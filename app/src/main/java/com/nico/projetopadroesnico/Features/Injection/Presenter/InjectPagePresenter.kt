package com.nico.projetopadroesnico.Features.Injection.Presenter

interface InjectPagePresenter {
    fun setView(injectPageView: InjectPageView)
    fun loadHomepage()
}