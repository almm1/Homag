package com.example.homag.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://gg-web.ru/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}