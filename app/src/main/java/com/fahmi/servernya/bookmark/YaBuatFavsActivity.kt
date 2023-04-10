package com.fahmi.servernya.bookmark

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahmi.databinding.ActivityFavuserApkfahmiDisiniBinding
import com.fahmi.semua_data_dan_model.ModelFavsUserFahmi
import com.fahmi.semua_data_dan_model.FahmiUserFahmi

import com.fahmi.userInterface_apkFahmi.Halaman_untuk_Detail.FahmiDetailUserActivity
import com.fahmi.userInterface_apkFahmi.halaman_utama.FahmiUserAdapterFahmi

class YaBuatFavsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavuserApkfahmiDisiniBinding
    private lateinit var adapter: FahmiUserAdapterFahmi
    private lateinit var viewModel: FahmiFavModelnya

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavuserApkfahmiDisiniBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = FahmiUserAdapterFahmi()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FahmiFavModelnya::class.java)

        adapter.setOnItemClickCallback(object : FahmiUserAdapterFahmi.OnItemClickCallback{
            override fun onItemClicked(data: FahmiUserFahmi) {
                Intent(this@YaBuatFavsActivity, FahmiDetailUserActivity::class.java).also {
                    it.putExtra(FahmiDetailUserActivity.YA_USERNAME_LOGIN, data.login)
                    it.putExtra(FahmiDetailUserActivity.YA_ID_USER, data.id)
                    it.putExtra(FahmiDetailUserActivity.YA_URL_FOTO, data.avatar_url)
                    startActivity(it)
                }
            }

        } )

        binding.apply {
            rvUserFahmi.setHasFixedSize(true)
        rvUserFahmi.layoutManager = LinearLayoutManager(this@YaBuatFavsActivity)
            rvUserFahmi.adapter = adapter

        }

        viewModel.getFavoriteUser()?.observe(this, {
            if (it!= null){
                val list = mapList(it)
                adapter.setList(list)
            }
        })

    }
    private fun mapList(users: List<ModelFavsUserFahmi>): ArrayList<FahmiUserFahmi>{
        val listUsers = ArrayList<FahmiUserFahmi>()
        for (user in users){
            val userMapped = FahmiUserFahmi(
                user.login,
                user.id,
                user.avatar_url

            )
            listUsers.add(userMapped)
        }
        return listUsers
    }
}