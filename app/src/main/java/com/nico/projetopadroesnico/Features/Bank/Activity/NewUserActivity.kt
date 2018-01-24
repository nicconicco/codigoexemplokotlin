package com.nico.projetopadroesnico.Features.Bank.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nico.projetopadroesnico.Common.Activity.BaseActivity
import com.nico.projetopadroesnico.Features.Bank.Model.User
import com.nico.projetopadroesnico.Features.Bank.Presenter.BankPresenter
import com.nico.projetopadroesnico.R
import kotlinx.android.synthetic.main.activity_new_user.*

class NewUserActivity : BaseActivity() {

    lateinit var presenter : BankPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)

        initView()
    }

    fun initView() {
        presenter = BankPresenter()

        btnAddUser.setOnClickListener {
            presenter.addUser(getNewUser())
        }
    }

    private fun getNewUser(): User {
        return User(name = tName.text.toString(), email = tEmail.text.toString())
    }
}
