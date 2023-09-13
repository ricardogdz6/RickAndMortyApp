package com.bupware.ricardogdztest.screens

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.saveable
import com.bupware.ricardogdztest.core.DTO.Character
import com.bupware.ricardogdztest.core.localHandler.LocalHandler
import com.bupware.ricardogdztest.core.retrofit.repository.CharacterRepository
import com.bupware.ricardogdztest.core.utils.sharedFunctions.sharedFunctions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    var characterList by savedStateHandle.saveable { mutableStateOf(emptyList<Character>()) }
    var searchingTerm by savedStateHandle.saveable { mutableStateOf("") }

    var characterListSearch by savedStateHandle.saveable { mutableStateOf(emptyList<Character>()) }
    var debounceHandler: Handler = Handler(Looper.getMainLooper())

    fun initHomeScreen(context:Context){

        val localHandler = LocalHandler(context)
        var localCharacters = emptyList<Character>()

        viewModelScope.launch {
            localCharacters = withContext(Dispatchers.Default){localHandler.getLocalCharacters()!!}

            if (localCharacters.isEmpty()){

                val remoteData = mutableListOf<Character>()

                viewModelScope.launch {

                    //If no data locally then we launch 2 http requests to get some data
                    val first = async {
                        val returningInfo = withContext(Dispatchers.Default) { CharacterRepository.getCharactersPagination() }
                        if (returningInfo != null) remoteData += returningInfo
                    }

                    val second = async {
                        val returningInfo =
                            withContext(Dispatchers.Default) {
                                CharacterRepository.getCharactersPaginationRandom(
                                    (2..40).random().toString()
                                )
                            }
                        if (returningInfo != null) remoteData += returningInfo
                    }

                    first.await()
                    second.await()

                    characterList = remoteData
                    localHandler.insertCharacters(characterList)

                }

            } else {

                //But if there's data and also Internet connection then we launch only 1 http request to get our HomeScreen a little different each time the app is opened
                if (sharedFunctions.isInternetAvailable(context)){

                    val remoteData = mutableListOf<Character>()

                    val first = async {
                        remoteData += localCharacters.subList(0,19)
                    }

                    val second = async {
                        val returningInfo =
                            withContext(Dispatchers.Default) {
                                CharacterRepository.getCharactersPaginationRandom(
                                    (2..40).random().toString()
                                )
                            }
                        if (returningInfo != null) remoteData += returningInfo
                    }

                    first.await()
                    second.await()

                    characterList = remoteData
                    localHandler.insertCharacters(characterList)

                } else{
                    //No internet, in this case all data shown is local
                    viewModelScope.launch {
                        val localCharacters = withContext(Dispatchers.Default){localHandler.getLocalCharacters()}
                        if (localCharacters != null) characterList = localCharacters
                    }

                }
            }
        }
    }

    fun searchTerm(term: String,context: Context) {

        // Remove last callback
        debounceHandler.removeCallbacksAndMessages(null)

        searchingTerm = term

        if (searchingTerm.isNotBlank()) {

            debounceHandler.postDelayed({
                // If no other call is made, this function will be executed after the delay
                searchCharactersByName(context)
            }, 300)

        } else characterListSearch = emptyList()

    }

    private fun searchCharactersByName(context: Context) {

        val localHandler = LocalHandler(context)

        viewModelScope.launch {
            val returningData = CharacterRepository.getCharactersByName(searchingTerm)
            if (!returningData.isNullOrEmpty()) {
                characterListSearch = returningData

                //And save locally
                localHandler.insertCharacters(returningData)
            }
        }
    }


}