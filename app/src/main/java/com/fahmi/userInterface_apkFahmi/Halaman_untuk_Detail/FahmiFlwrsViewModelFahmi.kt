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


class FahmiFlwrsViewModelFahmi : ViewModel(){
    val listFollowers = MutableLiveData<ArrayList<FahmiUserFahmi>>()

    fun AturKumpulanFolls(username:String){
        RetroKlienBoss.EPiAyUserGithubnyaInstance
            .getFollowers(username)
            .enqueue(object : Callback<ArrayList<FahmiUserFahmi>>{
                override fun onResponse(
                    call: Call<ArrayList<FahmiUserFahmi>>,
                    response: Response<ArrayList<FahmiUserFahmi>>,
                ) {
                    if (response.isSuccessful){
                        listFollowers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<FahmiUserFahmi>>, t: Throwable) {
                Log.d("Failure" , t.message.toString())
                }

            })
    }

    fun getKumpulanFolls(): LiveData<ArrayList<FahmiUserFahmi>>{
        return listFollowers
    }
}