package com.bupware.ricardogdztest.core.room.character

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)

    @Query("SELECT * FROM characters_table ORDER BY id ASC")
    fun readAllData(): Flow<List<Character>>


    @Query("SELECT * FROM characters_table WHERE id = :characterID")
    fun getCharacterByID(characterID : String) : Flow<Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters : List<Character>):  List<Long>
}