package com.bupware.ricardogdztest.core.room.character

import kotlinx.coroutines.flow.Flow

class CharacterRepository(private val characterDao: CharacterDao) {

    suspend fun insert(character: Character) {
        characterDao.insertCharacter(character)
    }

    suspend fun insertAll(characters: List<Character>) = characterDao.insertAll(characters)

    val readAllData : Flow<List<Character>> = characterDao.readAllData()

    fun getCharacterById(characterID : String) : Flow<Character> = characterDao.getCharacterByID(characterID)

}