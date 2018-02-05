package com.nico.projetopadroesnico.Features.InjectionSecondWay.Presenter

import okhttp3.OkHttpClient


interface OkHttpPresenter {
    fun getOkHttpClient() : OkHttpClient
}