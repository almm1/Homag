package com.example.homag.data.model

import com.google.gson.annotations.SerializedName

data class RegResponse(
    @SerializedName("USER_ID" ) var USERID  : String? = null,
    @SerializedName("MESSAGE" ) var MESSAGE : String? = null,
    @SerializedName("STATUS"  ) var STATUS  : String? = null
)