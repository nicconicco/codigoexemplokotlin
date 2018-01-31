package com.raywenderlich.android.droidwiki.dagger.AppAPI

import android.support.v4.app.FragmentActivity
import com.nico.projetopadroesnico.Features.Injection.Activity.InjectActivity
import com.nico.projetopadroesnico.Features.Injection.Fragment.InjectFragment
import com.raywenderlich.android.droidwiki.dagger.NetworkAPI.NetworkModule
import com.raywenderlich.android.droidwiki.dagger.WikiAPI.WikiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,
                    PresenterModule::class,
                    NetworkModule::class,
                    WikiModule::class])
interface AppComponent {
    fun inject(target: InjectActivity)
    fun injectFragment(target: InjectFragment)
}