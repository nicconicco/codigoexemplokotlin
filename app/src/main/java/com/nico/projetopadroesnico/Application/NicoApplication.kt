package com.nico.projetopadroesnico.Application

import android.app.Application
import com.nico.projetopadroesnico.Features.InjectionSecondWay.Dagger.*
import com.raywenderlich.android.droidwiki.dagger.AppAPI.AppComponent
import com.raywenderlich.android.droidwiki.dagger.AppAPI.AppModule
import com.raywenderlich.android.droidwiki.dagger.AppAPI.DaggerAppComponent


class NicoApplication: Application() {

    lateinit var wikiComponent: AppComponent
    private fun initDagger(app: NicoApplication): AppComponent =
            DaggerAppComponent.builder()
                    .appModule(AppModule(app))
                    .build()


    lateinit var netComponent: NetComponent
    private fun initDagger2(app: NicoApplication): NetComponent =
            DaggerNetComponent.builder()
                    .appModuleTwo(AppModuleTwo(app))
                    .netModule(NetModule("https://api.github.com"))
                    .presenter2Module(Presenter2Module())
                    .build()

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        wikiComponent = initDagger(this)
        netComponent = initDagger2(this)
    }

    companion object {
        var appInstance: NicoApplication? = null
        fun getInstance(): NicoApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configure a classe Application no AndroidManifest.xml")
            }

            return appInstance!!
        }
    }
}