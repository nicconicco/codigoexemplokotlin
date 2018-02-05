package com.nico.projetopadroesnico.Features.InjectionSecondWay.ServiceNetwork

import com.cognizant.dor.Common.Util.Rest
import com.cognizant.dor.Features.CommonUser.FirstAccess.Domain.ResponseInsure
import com.nico.projetopadroesnico.Common.Model.Const
import com.nico.projetopadroesnico.Features.Login.HttpService.LoginServiceOkhttp
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject


class InsureApi @Inject constructor(private val client: OkHttpClient)  {

    var OPERACOES_PROD = "api/listaOperadora"

    fun getInsures(): ResponseInsure? {
        if(Const.IS_PROD) {
            val json = doGet(Const.UTILS_PROD + OPERACOES_PROD)
            return Rest.convertResponse<ResponseInsure>(json)
        } else {
            val json = Rest.doGet(Const.BASE_URL + OPERACOES_PROD)
            return Rest.convertResponse<ResponseInsure>(json)
        }
    }

    private fun doGet(url: String): String? {
        return Request.Builder()
                .url(url)
                .get()
                .build()
                .let {
                    client.newCall(it).execute().body()?.string()
                }
    }
}