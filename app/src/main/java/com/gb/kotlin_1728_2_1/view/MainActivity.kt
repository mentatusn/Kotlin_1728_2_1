package com.gb.kotlin_1728_2_1.view

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.gb.kotlin_1728_2_1.R
import com.gb.kotlin_1728_2_1.databinding.ActivityMainBinding
import com.gb.kotlin_1728_2_1.lesson6.*
import com.gb.kotlin_1728_2_1.model.WeatherDTO
import com.gb.kotlin_1728_2_1.utils.BUNDLE_KEY
import com.gb.kotlin_1728_2_1.utils.BUNDLE_KEY_WEATHER
import com.gb.kotlin_1728_2_1.view.details.DetailsFragment
import com.gb.kotlin_1728_2_1.view.main.MainFragment
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val receiver = MyBroadcastReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(intent.getParcelableExtra<WeatherDTO>(BUNDLE_KEY_WEATHER)!=null){ // TODO что здесь происходит?
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.container,
                    DetailsFragment.newInstance(
                        Bundle().apply {
                            putParcelable(BUNDLE_KEY, intent.getParcelableExtra<WeatherDTO>(BUNDLE_KEY_WEATHER))
                        }
                    ))
                .addToBackStack("").commit()
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance()).commit()
        }
        val sp = getSharedPreferences("TAG",Context.MODE_PRIVATE)

        val activityP = getPreferences(Context.MODE_PRIVATE) // на уровне активити

        val appP = getDefaultSharedPreferences(this) // на уровне приложения

        appP.getString("key","")

        appP.edit().putString("key","value").apply()

        val editor = appP.edit()
        editor.putString("key1","value1")
        editor.putString("key2","value2")
        editor.putString("key3","value3")
        editor.putBoolean("key4",true)
        editor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_screen_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return return when (item.itemId) {
            R.id.menu_threads -> {
                supportFragmentManager.beginTransaction()
                    .add(R.id.container, ThreadsFragment.newInstance()).addToBackStack("").commit()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    }

}
