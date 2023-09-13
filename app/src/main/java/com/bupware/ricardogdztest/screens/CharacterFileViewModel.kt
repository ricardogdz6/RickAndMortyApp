package com.bupware.ricardogdztest.screens

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.saveable
import com.bupware.ricardogdztest.core.DTO.Character
import com.bupware.ricardogdztest.core.DTO.Location
import com.bupware.ricardogdztest.core.DTO.Origin
import com.bupware.ricardogdztest.core.localHandler.LocalHandler
import com.bupware.ricardogdztest.core.retrofit.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterFileViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    var currentCharacter by savedStateHandle.saveable { mutableStateOf(Character(0,"","","","","", Origin("",""), Location("",""),"", emptyList(),"","")) }

    fun initFile(characterID: Int, context:Context){

        val localHandler = LocalHandler(context)

        viewModelScope.launch {
            currentCharacter = localHandler.getLocalCharacterById(characterID)
        }
    }

}