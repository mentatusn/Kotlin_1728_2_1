package com.gb.kotlin_1728_2_1.lesson6

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.gb.kotlin_1728_2_1.databinding.FragmentThreadsBinding


class ThreadsFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    private var _binding: FragmentThreadsBinding? = null
    private val binding: FragmentThreadsBinding
        get() {
            return _binding!!
        }
    val myThread = MyThread()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myThread.start()
        binding.button.setOnClickListener {
            myThread.handler?.post {
                val result = startCalculations(3)
                activity?.let{ activity->
                    Handler(Looper.getMainLooper()).post {
                        binding.mainContainer.addView(TextView(activity).apply {
                            text = result
                        })
                    }
                }
            }
        }
    }

    class MyThread:Thread(){
        var handler: Handler?= null
        override fun run() {
            Looper.prepare()
            handler = Handler(Looper.myLooper()!!)
            Looper.loop()
        }
    }

    private fun startCalculations(seconds: Int): String {
        Thread.sleep(seconds*1000L)
        return "${seconds.toString()} ${Thread.currentThread().name}"
    }


    override fun onDestroy() {
        super.onDestroy()
        myThread.handler?.removeCallbacksAndMessages(null)
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThreadsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = ThreadsFragment()
    }
}