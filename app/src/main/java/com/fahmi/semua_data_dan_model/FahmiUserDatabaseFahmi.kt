package com.fahmi.semua_data_dan_model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ModelFavsUserFahmi::class],
        version = 1
    )
abstract class FahmiUserDatabaseFahmi: RoomDatabase() {
    companion object {
        var INSTANCE: FahmiUserDatabaseFahmi? = null

        fun getDatabase(context: Context): FahmiUserDatabaseFahmi? {
            if (INSTANCE == null) {
                synchronized(FahmiUserDatabaseFahmi::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FahmiUserDatabaseFahmi::class.java,
                        "fahmi_user_database"
                    ).build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun favsFahmiDao(): FavUserDaonyaDisiniCuy
}