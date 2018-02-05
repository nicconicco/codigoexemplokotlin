package com.nico.projetopadroesnico.Common.Presenter

interface BasePresenter<in V : BaseView> {
    fun detachView(view: V)
    fun attachView(view: V)
    fun destroy()
}