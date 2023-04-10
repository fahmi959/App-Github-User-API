package com.fahmi.userInterface_apkFahmi.Halaman_untuk_Detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.fahmi.databinding.ActivityFahmiDetailUserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FahmiDetailUserActivity : AppCompatActivity() {


    companion object {
        const val YA_USERNAME_LOGIN ="YA_USERNAME_login"
        const val YA_ID_USER = "YA_ID_user"
        const val YA_URL_FOTO = "YA_URL_foto"
    }



    private lateinit var binding: ActivityFahmiDetailUserBinding
    private  lateinit var viewModel : FahmiDetailUserViewModelFahmi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFahmiDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(YA_USERNAME_LOGIN)
        val id = intent.getIntExtra(YA_ID_USER, 0)
        val avatarUrl = intent.getStringExtra(YA_URL_FOTO)

        val bundle = Bundle()
        bundle.putString(YA_USERNAME_LOGIN, username)



        viewModel = ViewModelProvider(this).get(
            FahmiDetailUserViewModelFahmi::class.java)





        viewModel.setUserDetail(username)



        showLoading(true)
        viewModel.getUserDetail().observe(this, {
            if (it != null) {
                binding.apply {

                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvBio.text= it.bio
                    tvEmail.text = it.email
                    tvBlog.text = it.blog
                    tvLoc.text = "Lokasi"
                    tvRepos.text = "Perusahaan"
                    tvLocation.text = it.location
                    tvCompany.text = it.company
                    tvFollowers.text = "${it.followers} Followers"
                    tvFollowing.text = "${it.following} Following"
                    Glide.with(this@FahmiDetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(ivProfile)

                }

                showLoading(false)
            }
        })

        var _isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkUser(id)
            withContext(Dispatchers.Main){
                if (count != null) {
                    if (count > 0) {
                        binding.toggleFavsFahmiButton.isChecked = true
                        _isChecked = true
                    }else {binding.toggleFavsFahmiButton.isChecked = false
                        _isChecked =false

                    }
                }
            }
        }



        binding.toggleFavsFahmiButton.setOnClickListener{
            _isChecked = ! _isChecked
            if (_isChecked){
                viewModel.addToFavorit(username, id, avatarUrl)
            } else {
                viewModel.removeFromFavorit(id)
            }
            binding.toggleFavsFahmiButton.isChecked = _isChecked
        }

        val sectionPagerAdapter = FahmiSectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)

        }



    }


    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }



}