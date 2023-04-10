package com.fahmi.userInterface_apkFahmi.Halaman_untuk_Detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fahmi.servernya.RetroKlienBoss
import com.fahmi.semua_data_dan_model.FahmiUserFahmi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FahmiFlwingViewModelFahmi : ViewModel(){
    val listFollowing = MutableLiveData<ArrayList<FahmiUserFahmi>>()

    fun setListFollowing(username:String){
        RetroKlienBoss.EPiAyUserGithubnyaInstance
            .getFollowing(username)
            .enqueue(object : Callback<ArrayList<FahmiUserFahmi>>{
                override fun onResponse(
                    call: Call<ArrayList<FahmiUserFahmi>>,
                    response: Response<ArrayList<FahmiUserFahmi>>,
                ) {
                    if (response.isSuccessful){
                        listFollowing.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<FahmiUserFahmi>>, t: Throwable) {
                Log.d("Failure" , t.message.toString())
                }

            })
    }

    fun getListFollowing(): LiveData<ArrayList<FahmiUserFahmi>>{
        return listFollowing
    }
}