package com.fahmi.userInterface_apkFahmi.Halaman_untuk_Detail


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fahmi.servernya.RetroKlienBoss
import com.fahmi.semua_data_dan_model.FahmiUserDatabaseFahmi
import com.fahmi.semua_data_dan_model.ModelFavsUserFahmi
import com.fahmi.semua_data_dan_model.FavUserDaonyaDisiniCuy
import com.fahmi.semua_data_dan_model.FahmiDetailUserResponseFahmi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FahmiDetailUserViewModelFahmi(application: Application) : AndroidViewModel(application) {




    val user = MutableLiveData<FahmiDetailUserResponseFahmi>()

    private var userDaoFahmiViewModelz: FavUserDaonyaDisiniCuy?
    private var userDatabaseFahmiZ: FahmiUserDatabaseFahmi?

    init {
        userDatabaseFahmiZ = FahmiUserDatabaseFahmi.getDatabase(application)
        userDaoFahmiViewModelz = userDatabaseFahmiZ?.favsFahmiDao()
    }

    fun setUserDetail(username: String?) {
        RetroKlienBoss.EPiAyUserGithubnyaInstance
            .getUserDetail(username)
            .enqueue(object : Callback<FahmiDetailUserResponseFahmi> {
                override fun onResponse(
                    call: Call<FahmiDetailUserResponseFahmi>,
                    response: Response<FahmiDetailUserResponseFahmi>,
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<FahmiDetailUserResponseFahmi>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })


    }



    fun getUserDetail(): LiveData<FahmiDetailUserResponseFahmi> {
        return user
    }

    fun addToFavorit(username: String?, id:Int, avatarUrl: String?){
        CoroutineScope(Dispatchers.IO).launch {
            var user = ModelFavsUserFahmi(
                username,
                id,
                avatarUrl
            )
            userDaoFahmiViewModelz?.TambahFavs(user)
        }
    }
suspend fun checkUser(id: Int) =userDaoFahmiViewModelz?.checkUser(id)
    fun removeFromFavorit(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            userDaoFahmiViewModelz?.HapusDariFavsApkFahmi(id)

        }
    }




}