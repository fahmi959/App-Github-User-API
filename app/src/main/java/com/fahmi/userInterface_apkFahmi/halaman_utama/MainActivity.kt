package com.fahmi.userInterface_apkFahmi.halaman_utama

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahmi.R
import com.fahmi.semua_data_dan_model.FahmiUserFahmi
import com.fahmi.databinding.ActivityMainBinding
import com.fahmi.userInterface_apkFahmi.Halaman_untuk_Detail.FahmiDetailUserActivity
import com.fahmi.servernya.bookmark.YaBuatFavsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
private lateinit var viewModel: FahmiMainViewModelFahmi
private lateinit var adapter: FahmiUserAdapterFahmi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = FahmiUserAdapterFahmi()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : FahmiUserAdapterFahmi.OnItemClickCallback{
            override fun onItemClicked(data: FahmiUserFahmi) {
                Intent(this@MainActivity, FahmiDetailUserActivity::class.java).also {
                    it.putExtra(FahmiDetailUserActivity.YA_USERNAME_LOGIN, data.login)
                    it.putExtra(FahmiDetailUserActivity.YA_ID_USER, data.id)
                    it.putExtra(FahmiDetailUserActivity.YA_URL_FOTO, data.avatar_url)
               startActivity(it)
                }
            }

        } )


        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FahmiMainViewModelFahmi::class.java)

        binding.apply {
            recycleViewPadaMainActivityCuy.layoutManager = LinearLayoutManager(this@MainActivity)
            recycleViewPadaMainActivityCuy.setHasFixedSize(true)
            recycleViewPadaMainActivityCuy.adapter = adapter

            btnSearch.setOnClickListener{
        searchUser()
            }
            etQuery.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
       searchUser()
        return@setOnKeyListener true
                }
               return@setOnKeyListener false
            }
        }
viewModel.getSearchUser().observe(this, {
    if(it!= null){
        adapter.setList(it)
        showLoading(false)
    }
})
    }

    private fun searchUser(){
        binding.apply {
            val query = etQuery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModel.setSearchUsers(query)
        }
    }

    private fun showLoading(state: Boolean)
    {
        if (state){
            binding.progressBarMainActivity.visibility = View.VISIBLE

        }
        else  {binding.progressBarMainActivity.visibility = View.GONE
    }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_untuk_pengaturan_setting, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favs_menu_cuy -> {
                Intent(this, YaBuatFavsActivity::class.java).also {
                    startActivity(it)
                }
            }
            R.id.pengaturan_menu_cuy -> {
                Intent(this, PengaturanApkFahmiActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}