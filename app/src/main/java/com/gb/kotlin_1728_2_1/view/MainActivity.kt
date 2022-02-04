package com.gb.kotlin_1728_2_1.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.gb.kotlin_1728_2_1.R
import com.gb.kotlin_1728_2_1.databinding.ActivityMainBinding
import com.gb.kotlin_1728_2_1.lesson10.MapsFragment
import com.gb.kotlin_1728_2_1.lesson6.MyBroadcastReceiver
import com.gb.kotlin_1728_2_1.lesson6.ThreadsFragment
import com.gb.kotlin_1728_2_1.lesson9.ContentProviderFragment
import com.gb.kotlin_1728_2_1.model.WeatherDTO
import com.gb.kotlin_1728_2_1.room.App
import com.gb.kotlin_1728_2_1.utils.BUNDLE_KEY
import com.gb.kotlin_1728_2_1.utils.BUNDLE_KEY_WEATHER
import com.gb.kotlin_1728_2_1.view.details.DetailsFragment
import com.gb.kotlin_1728_2_1.view.history.HistoryFragment
import com.gb.kotlin_1728_2_1.view.main.MainFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val receiver = MyBroadcastReceiver()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance()).commit()
        }
    }

    fun getFCMToken(){
        // получаем токен
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("mylogs_push", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Log.d("mylogs_push", " token $token")
            // Log and toast
            /*  val msg = getString(R.string.msg_token_fmt, token)

              Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()*/
        })
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
            R.id.menu_history -> {
                supportFragmentManager.beginTransaction()
                    .add(R.id.container, HistoryFragment.newInstance()).addToBackStack("").commit()
                true
            }
            R.id.menu_content -> {
                supportFragmentManager.beginTransaction()
                    .add(R.id.container, ContentProviderFragment.newInstance()).addToBackStack("")
                    .commit()
                true
            }

            R.id.menu_google_maps -> {
                supportFragmentManager.beginTransaction()
                    .add(R.id.container, MapsFragment()).addToBackStack("").commit()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    }

}
