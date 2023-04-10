package com.fahmi.userInterface_apkFahmi.halaman_utama

import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.fahmi.R
import com.fahmi.semua_data_dan_model.PengingatApkFahmi
import com.fahmi.databinding.ActivityTampilanPengaturannyaBinding
import com.fahmi.preferensi_dan_provider_fahmi.PreferesiReminderFiturPengingat
import com.fahmi.servernya.retro_dan_epiay.PenerimaAlaremApkFahmi
import com.google.android.material.switchmaterial.SwitchMaterial

class PengaturanApkFahmiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTampilanPengaturannyaBinding

    private  lateinit var pengingatApkFahmi : PengingatApkFahmi
    private lateinit var penerimaAlaremApkFahmi: PenerimaAlaremApkFahmi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTampilanPengaturannyaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme_fahmi)

        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
                switchTheme.setText("Light Mode")
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }
        val preferesiReminderFiturPengingat = PreferesiReminderFiturPengingat(this)
        if (preferesiReminderFiturPengingat.getReminder().isReminded){
            binding.switchReminderFahmi.isChecked = true

        } else{
            binding.switchReminderFahmi.isChecked = false
        }
        penerimaAlaremApkFahmi = PenerimaAlaremApkFahmi()

        binding.switchReminderFahmi.setOnCheckedChangeListener {buttonView, isChecked ->
            if (isChecked){
                saveReminder(true)
                penerimaAlaremApkFahmi.setRepeatingAlarm(this, "RepeatingAlarm", "07:45", "Github pengingatApkFahmi")
            } else{
                saveReminder(false)
                penerimaAlaremApkFahmi.cancelAlarm(this)
            }
        }
    }

    private fun saveReminder(state: Boolean) {
val preferesiReminderFiturPengingat = PreferesiReminderFiturPengingat(this)
        pengingatApkFahmi = PengingatApkFahmi()

        pengingatApkFahmi.isReminded = state
        preferesiReminderFiturPengingat.setReminder(pengingatApkFahmi)
    }
}