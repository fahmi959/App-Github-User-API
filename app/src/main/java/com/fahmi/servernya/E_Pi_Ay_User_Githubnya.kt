package com.fahmi.servernya

import com.fahmi.semua_data_dan_model.FahmiDetailUserResponseFahmi
import com.fahmi.semua_data_dan_model.FahmiUserFahmi
import com.fahmi.semua_data_dan_model.FahmiUserResponseFahmi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface E_Pi_Ay_User_Githubnya {

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String

    ):Call<FahmiUserResponseFahmi>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
            @Path("username") username: String?
    ):Call<FahmiDetailUserResponseFahmi>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username:String
    ):Call<ArrayList<FahmiUserFahmi>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username:String
    ):Call<ArrayList<FahmiUserFahmi>>
}