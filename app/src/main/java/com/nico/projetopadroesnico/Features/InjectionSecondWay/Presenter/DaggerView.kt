package com.nico.projetopadroesnico.Features.InjectionSecondWay.Presenter

import com.cognizant.dor.Features.CommonUser.FirstAccess.Domain.Insure

interface DaggerView {

    //Progress
    fun showProgress()
    fun dissmissProgress()

    // Task
    fun updateView(insures: MutableList<Insure>)
    fun updateViewWithErrorServer(it: Int?, msgReturn: MutableList<String>?)
    fun exceptionHappens(t: Throwable?, responseLogin: Any? = null)
    fun noInternet()
    fun exceptionTimeOut(t: Throwable?)
}