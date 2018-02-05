package com.nico.projetopadroesnico.Features.InjectionSecondWay.Presenter

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class OkHttpPresenterImpl : OkHttpPresenter {
    override fun getOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(logging)

        return client.build()
    }
}