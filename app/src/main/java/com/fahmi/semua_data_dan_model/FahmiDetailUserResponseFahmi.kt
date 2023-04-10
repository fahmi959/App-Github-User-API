package com.fahmi.semua_data_dan_model

data class FahmiDetailUserResponseFahmi(
    val login: String,
    val id : Int,
    val avatar_url: String,
    val followers_url: String,
    val following_url: String,
    val name : String,
    val following: Int,
    val followers:Int,
    val bio: String,
    val location: String,
    val company: String,
    val email: String,
    val blog: String
)
