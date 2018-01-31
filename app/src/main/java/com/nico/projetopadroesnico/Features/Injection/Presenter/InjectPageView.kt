package com.nico.projetopadroesnico.Features.Injection.Presenter

import com.nico.projetopadroesnico.Features.Injection.Model.WikiHomepage

interface InjectPageView {
    fun displayLoading()

    fun dismissLoading()

    fun displayHomepage(result: WikiHomepage)

    fun displayError(error: String?)
}