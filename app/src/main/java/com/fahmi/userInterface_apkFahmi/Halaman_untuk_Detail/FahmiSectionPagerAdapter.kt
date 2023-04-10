@file:Suppress("DEPRECATION")

package com.fahmi.userInterface_apkFahmi.Halaman_untuk_Detail

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.fahmi.R

class FahmiSectionPagerAdapter (private val mCtx : Context, fm: FragmentManager, data: Bundle): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragmentBundle: Bundle
    init{
        fragmentBundle = data
    }

    @StringRes
private val TAB_JUDUL_PENAMAAN_MENU = intArrayOf(R.string.tab_1, R.string.tab_2)

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
      var fragmentFahmiz: Fragment? = null
        when(position){
            0 -> fragmentFahmiz = FahmiFollowersFragmentFahmi()
            1 -> fragmentFahmiz = FahmiFollowingFragmentFahmi()
        }
        fragmentFahmiz?.arguments = this.fragmentBundle
        return fragmentFahmiz as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mCtx.resources.getString(TAB_JUDUL_PENAMAAN_MENU[position])
    }
}