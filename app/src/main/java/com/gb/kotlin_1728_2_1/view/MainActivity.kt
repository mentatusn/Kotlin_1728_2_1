package com.gb.kotlin_1728_2_1.view

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.gb.kotlin_1728_2_1.R
import com.gb.kotlin_1728_2_1.databinding.ActivityMainBinding
import com.gb.kotlin_1728_2_1.lesson4.main
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
        if(intent.getParcelableExtra<WeatherDTO>(BUNDLE_KEY_WEATHER)!=null){
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
        startService(Intent(this, MyService::class.java).apply {
            //putExtra(MAIN_SERVICE_KEY_EXTRAS,"HEllo")
        })

        val manager = WorkManager.getInstance(this)
        val worker = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .build()
        manager.enqueue(worker)
        //manager.cancelWorkById(worker.id)
        //manager.cancelAllWorkByTag()
        //manager.cancelAllWork()

        registerReceiver(receiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        registerReceiver(receiver, IntentFilter("myAction"))

        sendBroadcast(Intent("myAction").apply {
            putExtra(MAIN_SERVICE_KEY_EXTRAS, "HEllo")
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
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    }

}
