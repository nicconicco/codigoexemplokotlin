package com.nico.projetopadroesnico.Common.Model

import com.google.gson.annotations.SerializedName

/**
 * Created by 653835 on 27/09/2017.
 */


class
GenericResponse {
    @SerializedName("CodigoStatus")
    var codeStatus: Int = 0

    @SerializedName("mensagemRetorno")
    var msgReturn2 : MutableList<String>? = null

    @SerializedName("MensagemRetorno")
    var msgReturn : MutableList<String>? = null
}