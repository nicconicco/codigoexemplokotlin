package com.nico.projetopadroesnico.Features.Bank.Activity
import android.os.Bundle
import com.google.gson.Gson
import com.nico.projetopadroesnico.Common.Activity.BaseActivity
import com.nico.projetopadroesnico.Common.Model.Const
import com.nico.projetopadroesnico.Features.Bank.Model.User
import com.nico.projetopadroesnico.Features.Bank.Presenter.BankPresenter
import com.nico.projetopadroesnico.R
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : BaseActivity() {

    lateinit var presenter : BankPresenter
    lateinit var oldUser : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        initView()
    }

    private fun initView() {
        presenter = BankPresenter()
        val extras = intent.extras
        extras?.apply {
            val value = extras.getString(Const.DETAIL_USER)
            oldUser = Gson().fromJson(value, User::class.java)

            tName.setText(oldUser.name)
            tEmail.setText(oldUser.email)
        }

        btnEdit.setOnClickListener{
            val userUpdate = User(name = tName.text.toString(), email = tEmail.text.toString())
            presenter.updateUser(context, userUpdate, oldUser)
        }

        btnBack.setOnClickListener{
            finish()
        }
    }
}
