package com.bupware.ricardogdztest.core.retrofit.repository

import android.util.Log
import com.bupware.ricardogdztest.core.DTO.Character
import com.bupware.ricardogdztest.core.retrofit.api.RetrofitClient
import com.bupware.ricardogdztest.core.retrofit.services.CharacterService
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.bupware.ricardogdztest.core.DTO.CharacterListResponse


object CharacterRepository {

    private val characterService = RetrofitClient.getRetrofit().create(CharacterService::class.java)

    suspend fun getCharactersPagination(): List<Character>? = suspendCancellableCoroutine { continuation ->
        characterService.getCharactersPagination().enqueue(object : Callback<CharacterListResponse> {
            override fun onResponse(call: Call<CharacterListResponse>, response: Response<CharacterListResponse>) {

                if (response.isSuccessful) {
                    continuation.resume(response.body()?.results,null)
                }

            }

            override fun onFailure(call: Call<CharacterListResponse>, t: Throwable) {
                continuation.cancel()
            }
        })
    }

    suspend fun getCharactersPaginationRandom(page:String): List<Character>? = suspendCancellableCoroutine { continuation ->
        characterService.getCharactersPaginationRandom(page).enqueue(object : Callback<CharacterListResponse> {
            override fun onResponse(call: Call<CharacterListResponse>, response: Response<CharacterListResponse>) {

                if (response.isSuccessful) {
                    continuation.resume(response.body()?.results,null)
                }

            }

            override fun onFailure(call: Call<CharacterListResponse>, t: Throwable) {
                continuation.cancel()
            }
        })
    }

    suspend fun getCharactersByName(name:String): List<Character>? = suspendCancellableCoroutine { continuation ->
        characterService.getCharactersByName(name).enqueue(object : Callback<CharacterListResponse> {
            override fun onResponse(call: Call<CharacterListResponse>, response: Response<CharacterListResponse>) {

                if (response.isSuccessful) {
                    continuation.resume(response.body()?.results,null)
                }

            }

            override fun onFailure(call: Call<CharacterListResponse>, t: Throwable) {
                continuation.cancel()
            }
        })
    }

    suspend fun getCharactersById(id:String): Character? = suspendCancellableCoroutine { continuation ->
        characterService.getCharacterById(id).enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {

                if (response.isSuccessful) {
                    continuation.resume(response.body(),null)
                }

            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                continuation.cancel()
            }
        })

    }

}


