package com.nico.projetopadroesnico.Features.InjectionSecondWay.Presenter

import android.app.Activity
import com.cognizant.dor.Common.Extensions.task.startTask
import com.cognizant.dor.Features.CommonUser.FirstAccess.Domain.ResponseInsure
import com.nico.projetopadroesnico.Common.Extension.isNetworkAvailable
import com.nico.projetopadroesnico.Features.InjectionSecondWay.ServiceNetwork.InsureApi
import okhttp3.OkHttpClient
import timber.log.Timber
import java.util.concurrent.TimeoutException

class DaggerPresenterImpl : DaggerPresenter {

    private lateinit var daggerView: DaggerView

    override fun startTaskFindList(activity: Activity, okHttpClient: OkHttpClient) {
        var response: ResponseInsure? = null
        daggerView.showProgress()
        if (activity.isNetworkAvailable()) {
            startTask(activity = activity, execute = {
                try {
                    response = InsureApi(okHttpClient).getInsures()
                } catch (e: Exception) {
                    Timber.e(e)
                    daggerView.exceptionHappens(e)
                } catch (e: TimeoutException) {
                    Timber.e(e)
                    daggerView.exceptionTimeOut(e)
                }
            }, updateView = {
                response?.let {
                    it.result?.let {
                        daggerView.updateView(it)
                    }
                }
            })
        } else {
            Timber.d("No internet")
            daggerView.noInternet()
        }
        daggerView.dissmissProgress()
    }

    override fun startTaskWithRetrofitFindList() {
        daggerView.showProgress()

        daggerView.dissmissProgress()
    }


    override fun setView(daggerView: DaggerView) {
        this.daggerView = daggerView
    }
}