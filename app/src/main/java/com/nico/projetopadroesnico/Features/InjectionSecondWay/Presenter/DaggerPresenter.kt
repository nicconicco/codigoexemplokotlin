package com.nico.projetopadroesnico.Features.InjectionSecondWay.Presenter

import android.app.Activity
import okhttp3.OkHttpClient

interface DaggerPresenter {
    fun setView(daggerView: DaggerView)
    fun startTaskFindList(activity: Activity, okHttpClient: OkHttpClient)
    fun startTaskWithRetrofitFindList()
}