package com.nico.projetopadroesnico.Features.Login.HttpService

import com.google.gson.GsonBuilder
import com.nico.projetopadroesnico.Common.Model.Const
import com.nico.projetopadroesnico.Features.Login.HttpService.LoginServiceOkhttp.AUTENTICATION
import com.nico.projetopadroesnico.Features.Login.HttpService.LoginServiceOkhttp.AUTENTICATION_PROD
import com.nico.projetopadroesnico.Features.Login.HttpService.LoginServiceOkhttp.PROD_ACESSO_LOGIN
import com.nico.projetopadroesnico.Features.Login.Model.ResponseLogin
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class LoginServiceRetrofit {

    /**
     *
     * MODE RETROFIT
     *
     */

    interface ApiServiceInterface {
        @POST(AUTENTICATION_PROD)
        fun doLoginProd(@Body json: String): Observable<ResponseLogin>

        @POST(AUTENTICATION)
        fun doLogin(@Body json: String): Observable<ResponseLogin>
    }

    val service: ApiServiceInterface

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder()
                .setLenient()
                .create()

        var baseUrl = ""

        if (Const.IS_PROD) {
            baseUrl = PROD_ACESSO_LOGIN
        } else {
            baseUrl = Const.BASE_URL
        }

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()

        service = retrofit.create<ApiServiceInterface>(ApiServiceInterface::class.java)
    }

    fun doLogin(json: String): Observable<ResponseLogin> {
        if (Const.IS_PROD) {
            return service.doLoginProd(json)
        } else {
            return service.doLogin(json)
        }
    }
}