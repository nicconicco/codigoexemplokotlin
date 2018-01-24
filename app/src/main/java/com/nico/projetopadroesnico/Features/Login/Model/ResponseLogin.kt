package com.nico.projetopadroesnico.Features.Login.Model

import com.google.gson.annotations.SerializedName

/**
 * Created by 653835 on 27/09/2017.
 */


open class ResponseLogin {

    @SerializedName("codigoStatus")
    var codigoStatus : Int = 0

    @SerializedName("mensagemRetorno")
    var msgReturn : MutableList<String>? = null

    @SerializedName("resultado")
    var result : Login? = null

}