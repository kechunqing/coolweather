package com.kcq.coolweather

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.kcq.coolweather.gson.Weather
import com.kcq.coolweather.main.WeatherActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val we = Weather()
        var pre = PreferenceManager.getDefaultSharedPreferences(this)
        if (pre.getString("weather", null) != null) {
            startActivity(Intent(this,WeatherActivity::class.java))
            finish()
        }
    }
}
