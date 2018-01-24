@file:Suppress("DEPRECATION")

package com.nico.projetopadroesnico.Features.Login.Presenter

import android.app.Activity
import android.content.Context
import br.com.livroandroid.carros.extensions.toJson
import com.cognizant.dor.Common.Extensions.task.startTask
import com.cognizant.dor.Common.Util.Rest
import com.nico.projetopadroesnico.Common.Extension.isNetworkAvailable
import com.nico.projetopadroesnico.Features.Login.HttpService.LoginServiceOkhttp
import com.nico.projetopadroesnico.Features.Login.HttpService.LoginServiceRetrofit
import com.nico.projetopadroesnico.Features.Login.Model.Profile
import com.nico.projetopadroesnico.Features.Login.Model.ResponseLogin
import com.nico.projetopadroesnico.Features.Home.Activity.HomeActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.startActivity
import retrofit2.adapter.rxjava2.HttpException
import timber.log.Timber
import java.util.concurrent.TimeoutException


class LoginPresenter {
    val subscriptions = CompositeDisposable()
    val TAG: String = "LoginPresenterView"

    interface LoginPresenterView {
        fun updateView(it: Profile?)
        fun updateViewWithErrorServer(it: Int?, msgReturn: MutableList<String>?)
        fun exceptionHappens(t: Throwable?, responseLogin: Any? = null)
        fun noInternet()
        fun exceptionTimeOut(t: Throwable?)
    }

    fun doLoginWithOkttp(activity: Activity, callback: LoginPresenterView, user: String, password: String) {
        startTaskLogin(activity, callback, user, password)
    }

    private fun startTaskLogin(activity: Activity, callback: LoginPresenterView, user: String, password: String) {
        var response: ResponseLogin? = null
        if (activity.isNetworkAvailable()) {
            startTask(activity = activity, execute = {
                try {
                    val json = mapOf(
                            "Usuario" to user,
                            "Senha" to password,
                            "TipoAcesso" to 0)

                    response = LoginServiceOkhttp.login(json.toJson())
                } catch (e: Exception) {
                    Timber.e(e)
                    callback.exceptionHappens(e)
                } catch (e: TimeoutException) {
                    Timber.e(e)
                    callback.exceptionTimeOut(e)
                }
            }, updateView = {
                returnOfLoginService(response, callback)
            })
        } else {
            Timber.d("No internet")
            callback.noInternet()
        }
    }

    fun returnOfLoginService(response: ResponseLogin?, callback: LoginPresenterView) {
        var response1 = response
        if (response1 != null) {
            response1.let {
                response1 = it
                if (it.codigoStatus != 200) {
                    val codigo = it.codigoStatus
                    response1?.msgReturn?.let {
                        callback.updateViewWithErrorServer(codigo, it)
                    }
                } else {
                    it.result?.let {
                        it.dadosUsuario?.let {
                            callback.updateView(it)
                        }
                    }
                }
            }
        } else {
            callback.updateView(null)
        }
    }

    inline fun <reified T>doLoginWithRetrofit(activity: Activity, callback: LoginPresenterView, user: String, password: String) {
        if (activity.isNetworkAvailable()) {
            val json = mapOf(
                    "Usuario" to user,
                    "Senha" to password,
                    "TipoAcesso" to 0)
            val observableLogin = LoginServiceRetrofit().doLogin(json.toJson())
            startTaskWithRetrofit<T>(callback, observableLogin)
        } else {
            callback.noInternet()
        }
    }

    inline fun <reified T>startTaskWithRetrofit(callback: LoginPresenterView, observableLogin: Observable<ResponseLogin>) {
        val subscribe = observableLogin.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ responseLogin: ResponseLogin? ->
                    returnOfLoginService(responseLogin, callback)
                },
                        { t: Throwable? ->
                            Timber.e(t)
                            if (t is TimeoutException) {
                                callback.exceptionTimeOut(t)
                            }
                            if (t is Exception) {
                                val error = t as HttpException
                                error.response().errorBody()?.let {
                                    val errorBody = it.string()
                                    val responseLogin = Rest.convertResponse<T>(errorBody)
                                    callback.exceptionHappens(t, responseLogin)
                                }
                            }
                        })
        subscriptions.add(subscribe)
    }


    fun showMasterActivity(context: Context) {
        context.startActivity<HomeActivity>()
    }
}