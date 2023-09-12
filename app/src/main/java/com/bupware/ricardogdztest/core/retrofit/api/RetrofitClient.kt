package com.bupware.ricardogdztest.core.retrofit.api

import com.google.gson.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://rickandmortyapi.com"

    var gson = GsonBuilder()
        .setLenient()
        .create()


    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    fun getRetrofit(): Retrofit = retrofit


}
