package com.bupware.ricardogdztest.core.retrofit.services

import com.bupware.ricardogdztest.core.DTO.Character
import com.bupware.ricardogdztest.core.DTO.CharacterListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {


    @GET("/api/character")
    fun getCharactersPagination(): Call<CharacterListResponse>

    @GET("/api/character")
    fun getCharactersPaginationRandom(@Query("page") page: String): Call<CharacterListResponse>

    @GET("/api/character/{page}")
    fun getCharacterById(@Path("page") page: String): Call<Character>

    @GET("/api/character")
    fun getCharactersByName(@Query("name") name: String): Call<CharacterListResponse>

}