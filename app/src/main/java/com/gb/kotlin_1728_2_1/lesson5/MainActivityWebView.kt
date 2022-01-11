package com.gb.kotlin_1728_2_1.lesson5

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.gb.kotlin_1728_2_1.databinding.ActivityMainWebviewBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MainActivityWebView : AppCompatActivity() {


    lateinit var binding: ActivityMainWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnOk.setOnClickListener {
            request(binding.etUrl.text.toString())
        }
    }

    private fun request(urlString: String) {
        /*try {
 // работаем здесь
        }catch (){
            // выводим ошибки
        } finally {
            httpsURLConnection.disconnect()
        }*/

        val handlerCurrent1 = Handler(Looper.myLooper()!!)
        Thread {
            val url = URL(urlString)
            val httpsURLConnection = (url.openConnection() as HttpsURLConnection).apply {
                requestMethod = "GET"
                readTimeout = 2000
            }
            val bufferedReader = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
            val result = convertBufferToResult(bufferedReader)
            runOnUiThread {
                binding.webView.loadDataWithBaseURL(
                    null,
                    result,
                    "text/html; charset=utf-8",
                    "utf-8",
                    null
                )
            }
            val handlerMainUI1 = Handler(mainLooper)
            val handlerMainUI2 = Handler(Looper.getMainLooper())
            handlerMainUI1.post {
                binding.webView.loadDataWithBaseURL(
                    null,
                    result,
                    "text/html; charset=utf-8",
                    "utf-8",
                    null
                )
            }
            handlerCurrent1.post {
                binding.webView.loadDataWithBaseURL(
                    null,
                    result,
                    "text/html; charset=utf-8",
                    "utf-8",
                    null
                )
            }
            handlerCurrent1.post {
                //binding.webView.loadUrl(url.path) // FIXME открыть на месте
            }

            httpsURLConnection.disconnect()
        }.start()
    }

    private fun convertBufferToResult(bufferedReader: BufferedReader): String {
        return bufferedReader.lines().collect(Collectors.joining("\n"))
    }
}
