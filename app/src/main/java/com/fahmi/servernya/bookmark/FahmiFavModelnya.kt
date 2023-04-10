package com.fahmi.servernya.bookmark

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.fahmi.semua_data_dan_model.FahmiUserDatabaseFahmi
import com.fahmi.semua_data_dan_model.ModelFavsUserFahmi
import com.fahmi.semua_data_dan_model.FavUserDaonyaDisiniCuy

class FahmiFavModelnya (application: Application) : AndroidViewModel(application){
    private var userDaoZfahmi: FavUserDaonyaDisiniCuy?
    private var userDatabaseZfahmi: FahmiUserDatabaseFahmi?

    init {
        userDatabaseZfahmi = FahmiUserDatabaseFahmi.getDatabase(application)
        userDaoZfahmi = userDatabaseZfahmi?.favsFahmiDao()
    }

    fun getFavoriteUser(): LiveData<List<ModelFavsUserFahmi>>?{
        return userDaoZfahmi?.DapatkanFavsUserMsukinDatabaseFahmi()
    }
}