package com.raywenderlich.android.droidwiki.dagger.AppAPI

import com.nico.projetopadroesnico.Features.Injection.Presenter.InjectPresenterImpl
import com.nico.projetopadroesnico.Features.Injection.Presenter.InjectPagePresenter
import com.raywenderlich.android.droidwiki.network.InjectPageNetwork
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {
    @Provides
    @Singleton
    fun provideInjectPresenter(injectPageNetwork: InjectPageNetwork): InjectPagePresenter = InjectPresenterImpl(injectPageNetwork)
}