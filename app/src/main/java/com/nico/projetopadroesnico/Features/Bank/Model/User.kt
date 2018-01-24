package com.nico.projetopadroesnico.Features.Bank.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("id")
    @Expose
    var id: String? = "0"
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null

    constructor(id: String? = "0", name: String? = "", email: String? = "") {
        this.id = id
        this.name = name
        this.email = email
    }
}