package com.raywenderlich.android.droidwiki.dagger.WikiAPI

import com.raywenderlich.android.droidwiki.network.InjectPageNetwork
import com.nico.projetopadroesnico.Features.Injection.NetworkCalls.WikiApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WikiModule {
    @Provides
    @Singleton
    fun provideHomepage(api: WikiApi) = InjectPageNetwork(api)
}