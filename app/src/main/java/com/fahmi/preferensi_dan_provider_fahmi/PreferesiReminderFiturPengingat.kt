package com.fahmi.preferensi_dan_provider_fahmi

import android.content.Context
import com.fahmi.semua_data_dan_model.PengingatApkFahmi

class PreferesiReminderFiturPengingat(context: Context) {

    companion object{
        const val PREFS_NAME_FAHMI = "reminder_pref_fahmi"
        private const val REMINDER_FAHMI = "isRemind_fahmi"
    }
   private val preference = context.getSharedPreferences(PREFS_NAME_FAHMI, Context.MODE_PRIVATE)

    fun setReminder(value: PengingatApkFahmi){
        val  editor = preference.edit()
editor.putBoolean(REMINDER_FAHMI, value.isReminded)
        editor.apply()
    }

    fun getReminder(): PengingatApkFahmi {
        val model = PengingatApkFahmi()
        model.isReminded = preference.getBoolean(REMINDER_FAHMI,false)
        return model
    }
}