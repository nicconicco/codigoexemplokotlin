package com.nico.projetopadroesnico.Features.Login.Activity

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import com.cognizant.dor.Common.Extensions.isDebuggable
import com.nico.projetopadroesnico.Common.Activity.BaseActivity
import com.nico.projetopadroesnico.Features.Login.Model.Profile
import com.nico.projetopadroesnico.Features.Login.Model.ResponseLogin
import com.nico.projetopadroesnico.Features.Login.Presenter.LoginPresenter
import com.nico.projetopadroesnico.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

@Suppress("DEPRECATION")
class LoginActivity : BaseActivity(), LoginPresenter.LoginPresenterView {

    lateinit var presenter: LoginPresenter
    lateinit var dialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {
        presenter = LoginPresenter()


        if(this.isDebuggable()) {
            etNumberCard.setText("771024013439003")
            etPassword.setText("123456")
        }

        btnNoLogin.setOnClickListener {
            presenter.showMasterActivity(context)
        }

        btnLoginOkhttp.setOnClickListener {
            dialog = ProgressDialog.show(this, "", "Aguarde...", false, false)
            showDialog(dialog)
            presenter.doLoginWithOkttp(this, this, etNumberCard.text.toString(), etPassword.text.toString())
        }

        btnLoginRetrofit.setOnClickListener {
            dialog = ProgressDialog.show(this, "", "Aguarde...", false, false)
            showDialog(dialog)
            presenter.doLoginWithRetrofit<ResponseLogin>(this, this, etNumberCard.text.toString(), etPassword.text.toString())
        }
    }

    override fun updateView(it: Profile?) {
        cancelDialog(dialog)
        it?.let {
            Log.d(presenter.TAG, "Logged!")
        }
    }

    override fun noInternet() {
        cancelDialog(dialog)
        toast("Sem internet, ligue para poder realizar o login")
    }

    override fun updateViewWithErrorServer(it: Int?, msgReturn: MutableList<String>?) {
        cancelDialog(dialog)
        it.let {
            toast("Ih deu esse erro aqui: $it e a mensagem foi: $msgReturn")
        }
    }
    override fun exceptionHappens(t: Throwable?, responseLogin: Any?) {
        val rl = responseLogin as ResponseLogin?
        rl?.let {
            cancelDialog(dialog)
            t?.let {
                toast("Estorou uma excessão: ${t.localizedMessage}")
            }
            responseLogin?.let {
                toast("Deu erro! e ele foi o ${responseLogin.codigoStatus} e a mensagem do servidor foi esta: ${responseLogin.msgReturn}")
            }
        }
    }

    override fun exceptionTimeOut(t: Throwable?) {
        cancelDialog(dialog)
        showSnack(this)
    }
}
