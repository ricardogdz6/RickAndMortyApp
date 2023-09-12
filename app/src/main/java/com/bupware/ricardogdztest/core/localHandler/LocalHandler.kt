package com.bupware.ricardogdztest.core.localHandler

import android.content.Context
import android.util.Log
import com.bupware.ricardogdztest.core.DTO.Character as CharacterDTO
import com.bupware.ricardogdztest.core.room.character.Character as CharacterLocal
import com.bupware.ricardogdztest.core.converter.Converter
import com.bupware.ricardogdztest.core.room.WDDatabase
import com.bupware.ricardogdztest.core.room.character.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.Serializable

class LocalHandler(val context: Context): Serializable {

    val room = WDDatabase.getDatabase(context = context)

    suspend fun getLocalCharacters(): List<CharacterDTO>? {

        val characters = mutableListOf<CharacterDTO>()

        GlobalScope.launch {
            val characterRoom = withContext(Dispatchers.IO) {CharacterRepository(room.characterDao()).readAllData.collect {
                it.forEach { characters.add(Converter.characterLocalToDto(it)) }}
            }
        }

        return characters

    }

    suspend fun insertCharacters(characters:List<CharacterDTO>){

        val charactersLocal = mutableListOf<CharacterLocal>()
        characters.forEach { charactersLocal.add(Converter.characterDtoToLocal(it)) }

        CharacterRepository(room.characterDao()).insertAll(charactersLocal)
    }


}