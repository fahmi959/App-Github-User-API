package com.fahmi.semua_data_dan_model

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavUserDaonyaDisiniCuy {
    @Insert
     fun TambahFavs(modelFavsUserFahmi: ModelFavsUserFahmi)

    @Query("SELECT * FROM fav_user_apk_fahmi_github")
    fun DapatkanFavsUserMsukinDatabaseFahmi(): LiveData<List<ModelFavsUserFahmi>>

    @Query("SELECT count(*) FROM fav_user_apk_fahmi_github WHERE fav_user_apk_fahmi_github.id = :id")
 fun checkUser(id: Int): Int

@Query("DELETE FROM fav_user_apk_fahmi_github WHERE fav_user_apk_fahmi_github.id = :id")
fun HapusDariFavsApkFahmi(id: Int): Int

    @Query("SELECT * FROM fav_user_apk_fahmi_github")
    fun findAll(): Cursor

}