package com.nico.projetopadroesnico.Features.Login.HttpService

import br.com.livroandroid.carros.extensions.toJson
import com.cognizant.dor.Common.Util.Rest.convertResponse
import com.cognizant.dor.Common.Util.Rest.doPost
import com.google.gson.GsonBuilder
import com.nico.projetopadroesnico.Common.Model.Const
import com.nico.projetopadroesnico.Common.Model.GenericResponse
import com.nico.projetopadroesnico.Features.Login.Model.Login
import com.nico.projetopadroesnico.Features.Login.Model.ResponseLogin
import io.reactivex.Observable
import okhttp3.Call
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 * Created by nicolaugalves on 19/09/17.
 */


object LoginServiceOkhttp {
    //    const val PROD_ACESSO_LOGIN = "https://acessoapp-acessoapp-hml.azurewebsites.net/"
    const val PROD_ACESSO_LOGIN = "https://acesso.dorconsultoria.com.br/"
    const val AUTENTICATION_PROD = "api/login"
    const val AUTENTICATION = "Acesso/api/login"

    val JSON = MediaType.parse("application/json; charset=utf-8")
    var client = OkHttpClient()


    /**
     *
     * MODE : OKHTTP
     *
     */
    @Throws(IOException::class)
    fun login(json: String): ResponseLogin? {
        configTimeOut()
        if (Const.IS_PROD) {
            val json = doPost(json, PROD_ACESSO_LOGIN + AUTENTICATION_PROD)
            return convertResponse<ResponseLogin>(json)
        } else {
            val json = doPost(json, Const.BASE_URL + AUTENTICATION)
            return convertResponse<ResponseLogin>(json)
        }
    }

    //-------------------------------------------------------------------------------------//
    const val CARD_PROD = "api/gerarCarteirinha/Gerar"
    private const val CARD = "Segurado/api/gerarCarteirinha/Gerar"
    fun createCards(json: Map<String, Any>, accesToken: String, username: String): GenericResponse? {

        if (Const.IS_PROD) {
            val json = doPost(json.toJson(), Const.URL_SEGURADO_PROD + CARD_PROD, accesToken, username)
            return convertResponse<GenericResponse>(json)
        } else {
            val json = doPost(json.toJson(), Const.BASE_URL + CARD, accesToken, username)
            return convertResponse<GenericResponse>(json)
        }
    }

    private fun configTimeOut() {
        val builder = OkHttpClient.Builder()
        val timeout: Long = 30
        builder.connectTimeout(timeout, TimeUnit.SECONDS)
        builder.readTimeout(timeout, TimeUnit.SECONDS)
        builder.writeTimeout(timeout, TimeUnit.SECONDS)
        client = builder.build()
    }
}