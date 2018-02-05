package com.cognizant.dor.Features.CommonUser.FirstAccess.Domain

import com.google.gson.annotations.SerializedName

/**
 * Created by 653835 on 27/09/2017.
 */


class Insure {
    @SerializedName("Nome")
    var name: String? = ""

    @SerializedName("CNPJ")
    var Cnpj: String? = ""
}