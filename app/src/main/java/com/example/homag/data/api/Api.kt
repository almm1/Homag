package com.example.homag.data.api

import com.example.homag.data.model.AuthResponse
import com.example.homag.data.model.RegResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("auth.php")
    fun auth( @Field("login") login: String,
              @Field("password") password: String,): Call<AuthResponse>

    @FormUrlEncoded
    @POST("reg.php")
    fun reg(
        @Field("NAME") name: String,
        @Field("PERSONAL_GENDER") gender: String,
        @Field("PERSONAL_BIRTHDAY") birthday: String,
        @Field("PERSONAL_MOBILE") mobile: String,
        @Field("EMAIL") email: String,
        @Field("WORK_COMPANY") company: String,
        @Field("UF_SERVICE_NUMBER") serviceNumber: String,
        @Field("LOGIN") login: String,
        @Field("PASSWORD") password: String,
        @Field("CONFIRM_PASSWORD") confirmPassword: String
    ): Call<RegResponse>
}