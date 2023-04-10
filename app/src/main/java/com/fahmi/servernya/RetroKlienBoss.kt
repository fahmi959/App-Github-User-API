package com.fahmi.servernya

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroKlienBoss {
    private const val BASE_URL= "https://api.github.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val EPiAyUserGithubnyaInstance = retrofit.create(E_Pi_Ay_User_Githubnya::class.java)
}