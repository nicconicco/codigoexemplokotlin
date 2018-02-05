package com.cognizant.dor.Features.CommonUser.FirstAccess.Domain

import com.google.gson.annotations.SerializedName

/**
 * Created by 653835 on 27/09/2017.
 */


class ResponseInsure {

    @SerializedName("CodigoStatus")
    var codeStatus: String? = ""

    @SerializedName("MensagemRetorno")
    var msgReturn : MutableList<String>? = null

    @SerializedName("Resultado")
    var result : MutableList<Insure>? = null
}