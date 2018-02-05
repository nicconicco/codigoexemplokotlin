package com.nico.projetopadroesnico.Features.InjectionSecondWay.Activity

import android.app.ProgressDialog
import android.content.SharedPreferences
import android.os.Bundle
import com.cognizant.dor.Features.CommonUser.FirstAccess.Domain.Insure
import com.nico.projetopadroesnico.Application.NicoApplication
import com.nico.projetopadroesnico.Common.Activity.BaseActivity
import com.nico.projetopadroesnico.Features.InjectionSecondWay.Presenter.DaggerPresenter
import com.nico.projetopadroesnico.Features.InjectionSecondWay.Presenter.DaggerView
import com.nico.projetopadroesnico.Features.InjectionSecondWay.Presenter.OkHttpPresenter
import com.nico.projetopadroesnico.R
import timber.log.Timber
import javax.inject.Inject


class Dagger2Activity : BaseActivity(), DaggerView {

    @Inject lateinit var presenter: DaggerPresenter
    @Inject lateinit var shared: SharedPreferences
    @Inject lateinit var okHttp: OkHttpPresenter

    lateinit var dialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger2)

      NicoApplication.getInstance().netComponent.inject(this)

        val a = shared
        a?.let {
            Timber.v("opa")
        }

        presenter.setView(this)
        okHttp?.let {
            presenter.startTaskFindList(this, okHttp.getOkHttpClient())
        }
    }

    override fun showProgress() {
        dialog = ProgressDialog.show(context, "", "Aguarde...", false, false)
        dialog.show()
    }

    override fun updateView(insures: MutableList<Insure>) {

    }

    override fun dissmissProgress() {
        dialog.dismiss()
    }


    override fun updateViewWithErrorServer(it: Int?, msgReturn: MutableList<String>?) {

    }

    override fun exceptionHappens(t: Throwable?, responseLogin: Any?) {

    }

    override fun noInternet() {

    }

    override fun exceptionTimeOut(t: Throwable?) {

    }

}
