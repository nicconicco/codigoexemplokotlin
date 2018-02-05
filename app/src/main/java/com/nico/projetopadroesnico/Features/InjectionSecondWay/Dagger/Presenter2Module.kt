package com.nico.projetopadroesnico.Features.InjectionSecondWay.Dagger

import com.nico.projetopadroesnico.Features.InjectionSecondWay.Presenter.DaggerPresenter
import com.nico.projetopadroesnico.Features.InjectionSecondWay.Presenter.DaggerPresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class Presenter2Module {

    @Singleton
    @Provides
    fun providePresenter2Module() : DaggerPresenter = DaggerPresenterImpl()
}