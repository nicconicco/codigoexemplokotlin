package com.nico.projetopadroesnico.Features.Login.Model

import com.google.gson.annotations.SerializedName

/**
 * Created by 653835 on 29/09/2017.
 */

class Profile(token: String = "") {

    var accessToken: String = token
    var carteirinha: String = ""

    //WS

    @SerializedName("Nome")
    var name : String = ""

    @SerializedName("MiniCarteirinha")
    var miniCarteirinha : String = ""

    @SerializedName("Login")
    var login : String = ""
}