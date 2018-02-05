package com.nico.projetopadroesnico.Features.InjectionSecondWay.Dagger

import com.nico.projetopadroesnico.Features.InjectionSecondWay.Activity.Dagger2Activity
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModuleTwo::class, NetModule::class, Presenter2Module::class])
interface NetComponent {
    fun inject(dagger2Activity: Dagger2Activity)
}