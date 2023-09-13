package com.bupware.ricardogdztest.core.room

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bupware.ricardogdztest.core.room.character.Character
import com.bupware.ricardogdztest.core.room.character.CharacterDao
import com.bupware.ricardogdztest.core.room.converter.DataConverter


@Database(entities = [Character::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class WDDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao


    companion object {
        @Volatile
        private var INSTANCE: WDDatabase? = null

        fun getDatabase(context: Context): WDDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                // Create database here
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    WDDatabase::class.java,
                    "ricardogdzTest_database"

                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}