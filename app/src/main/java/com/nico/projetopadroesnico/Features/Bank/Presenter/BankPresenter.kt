package com.nico.projetopadroesnico.Features.Bank.Presenter

import android.content.Context
import br.com.livroandroid.carros.extensions.toJson
import com.mobologicplus.kotlinmvpandroid.db.DbManager
import com.nico.projetopadroesnico.Common.Model.Const
import com.nico.projetopadroesnico.Features.Bank.Activity.NewUserActivity
import com.nico.projetopadroesnico.Features.Bank.Activity.UserDetailActivity
import com.nico.projetopadroesnico.Features.Bank.Model.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.startActivity
import timber.log.Timber


class BankPresenter  {

    interface BankPresenterView {
        fun updateView(listFriends: List<User>)
        fun exceptionHappens(t: Throwable?)
    }

    private val subscriptions = CompositeDisposable()

    fun loadFriendsDb(callback: BankPresenterView) {
        val observableFriends = DbManager().loadFriends()
        updateViewFromDb(callback, observableFriends)
    }

    fun updateViewFromDb(callback: BankPresenterView, observableFriends: Observable<List<User>>) {
        val subscribe = observableFriends.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listUsers: List<User>? ->
                    listUsers?.let {
                        callback.updateView(it)
                    }
                },
                        { t: Throwable? ->
                            t?.let {
                                Timber.e(t)
                                // TODO: fazer uma classe com respostas padroes para as exceptions
                                callback.exceptionHappens(t)
                            }
                        })
        subscriptions.add(subscribe)
    }

    fun addUser(user: User) {
        DbManager().saveFriendInList(user)
    }

    fun deleteUser(callback: BankPresenterView, user: User) {
        if(DbManager().deleteFriendInList(user)) {
            loadFriendsDb(callback)
        }
    }

    fun showAddUserActivity(context: Context?) {
        context?.let {
            context.startActivity<NewUserActivity>()
        }
    }

    fun showDetailUser(context: Context?, user: User) {
        context?.let {
            context.startActivity<UserDetailActivity>(Const.DETAIL_USER to user.toJson())
        }
    }

    fun updateUser(context: Context, user: User, oldUser: User) {
        DbManager().updateUser(context, user, oldUser)
    }
}