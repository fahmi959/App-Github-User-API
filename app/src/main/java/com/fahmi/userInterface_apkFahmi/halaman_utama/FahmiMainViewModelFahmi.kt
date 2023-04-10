package com.fahmi.userInterface_apkFahmi.halaman_utama

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fahmi.servernya.RetroKlienBoss
import com.fahmi.semua_data_dan_model.FahmiUserFahmi
import com.fahmi.semua_data_dan_model.FahmiUserResponseFahmi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FahmiMainViewModelFahmi :ViewModel() {

    val listUsers = MutableLiveData<ArrayList<FahmiUserFahmi>>()

    fun setSearchUsers(query:String)
    {RetroKlienBoss.EPiAyUserGithubnyaInstance
        .getSearchUsers(query)
        .enqueue(object : Callback<FahmiUserResponseFahmi>{
            override fun onResponse(
                call: Call<FahmiUserResponseFahmi>,
                response: Response<FahmiUserResponseFahmi>,
            ) {
              if(response.isSuccessful){
                  listUsers.postValue((response.body())?.items)
              }
            }

            override fun onFailure(call: Call<FahmiUserResponseFahmi>, t: Throwable) {
            Log.d("Failure", t.message.toString())
            }

        })
    }

    fun getSearchUser():LiveData<ArrayList<FahmiUserFahmi>>{
        return listUsers
    }

}