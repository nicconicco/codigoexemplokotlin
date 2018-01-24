package com.nico.projetopadroesnico.Features.Login.Model

import com.google.gson.annotations.SerializedName

/**
 * Created by 653835 on 28/09/2017.
 */

class Login (accesToken: String = "", username: String = "") {
    @SerializedName("access_token")
    var accessToken: String = accesToken

    @SerializedName("userName")
    var carteirinha: String = username

    @SerializedName("dadosUsuario")
    var dadosUsuario: Profile? = null
}