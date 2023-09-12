package com.bupware.ricardogdztest.core.room.converter

import androidx.room.TypeConverter
import com.bupware.ricardogdztest.core.room.character.Character
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.sql.Date

object DataConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromCharacter(character: Character): String {
        return gson.toJson(character)
    }

    @TypeConverter
    fun toCharacter(characterString: String): Character {
        return gson.fromJson(characterString, Character::class.java)
    }


}

