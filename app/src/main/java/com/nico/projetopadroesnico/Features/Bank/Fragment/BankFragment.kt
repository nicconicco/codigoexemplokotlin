package com.nico.projetopadroesnico.Features.Bank.Fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.livroandroid.carros.extensions.toJson
import com.nico.projetopadroesnico.Common.Fragment.BaseFragment
import com.nico.projetopadroesnico.Features.Bank.Adapter.FriendsAdapter
import com.nico.projetopadroesnico.Features.Bank.Model.User
import com.nico.projetopadroesnico.Features.Bank.Presenter.BankPresenter
import com.nico.projetopadroesnico.R
import kotlinx.android.synthetic.main.activity_bank.*
import timber.log.Timber


class BankFragment: BaseFragment(), BankPresenter.BankPresenterView, FriendsAdapter.onItemClickListener  {

    override fun itemRemoveClick(user: User) {
        Timber.d(user.toJson())
        presenter.deleteUser(this, user)
    }

    override fun itemDetail(user: User) {
        presenter.showDetailUser(context, user)
        Timber.d(user.toJson())
    }

    lateinit var presenter: BankPresenter
    private var isVisibleToUser = false

    override fun exceptionHappens(t: Throwable?) {
    }

    override fun updateView(friends: List<User>) {
       Timber.d(friends.toJson())
        context?.let {
            val adapter = FriendsAdapter(this, it, friends.toMutableList())
            recyclerView.layoutManager = LinearLayoutManager(it)
            recyclerView.adapter = adapter
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, icicle: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bank, container, false)
    }

    override fun onResume() {
        super.onResume()
        if (isVisibleToUser) {
            initView()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        if (isVisibleToUser) {
            initView()
        }
    }

    private fun initView() {
        presenter = BankPresenter()
        presenter.loadFriendsDb(this)

        btnAddUser.setOnClickListener {
            presenter.showAddUserActivity(context)
        }
    }
}