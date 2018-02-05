package com.nico.projetopadroesnico.Features.InjectionSecondWay.Dagger

import android.app.Application
import android.content.Context
import dagger.Module
import javax.inject.Singleton
import dagger.Provides


@Module
class AppModuleTwo(private val mContext: Application) {

    @Provides
    fun provideApplication(): Application {
        return mContext
    }

    @Provides
    fun provideContext(): Context {
        return mContext
    }
}