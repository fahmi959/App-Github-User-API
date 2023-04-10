package com.fahmi.userInterface_apkFahmi.Halaman_untuk_Detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahmi.R
import com.fahmi.databinding.FragmentFollowersDanFollowingApkfahmiBinding
import com.fahmi.userInterface_apkFahmi.halaman_utama.FahmiUserAdapterFahmi

class FahmiFollowersFragmentFahmi: Fragment(R.layout.fragment_followers_dan_following_apkfahmi) {

    private  var _binding : FragmentFollowersDanFollowingApkfahmiBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModelFoloww: FahmiFlwrsViewModelFahmi
    private lateinit var adapter: FahmiUserAdapterFahmi
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        username = args?.getString(FahmiDetailUserActivity.YA_USERNAME_LOGIN).toString()


        _binding = FragmentFollowersDanFollowingApkfahmiBinding.bind(view)



        adapter = FahmiUserAdapterFahmi()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvUserFhm.setHasFixedSize(true)
            rvUserFhm.layoutManager = LinearLayoutManager(activity)
            rvUserFhm.adapter = adapter
        }

        menampilanLoadingBar(true)
        viewModelFoloww = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FahmiFlwrsViewModelFahmi::class.java)
        viewModelFoloww.AturKumpulanFolls(username)
        viewModelFoloww.getKumpulanFolls().observe(viewLifecycleOwner ,{
            if(it!=null){
                adapter.setList(it)
                menampilanLoadingBar(false)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
    }

    private fun menampilanLoadingBar(state: Boolean)
    {
        if (state){
            binding.progressBarFahmi.visibility = View.VISIBLE

        }
        else  {binding.progressBarFahmi.visibility = View.GONE
        }

    }

}