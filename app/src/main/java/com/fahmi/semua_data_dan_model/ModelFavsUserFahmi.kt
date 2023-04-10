package com.fahmi.semua_data_dan_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "fav_user_apk_fahmi_github")
data class ModelFavsUserFahmi(
    val login: String?,
    @PrimaryKey
    val id: Int,
    val avatar_url: String?

): Serializable
