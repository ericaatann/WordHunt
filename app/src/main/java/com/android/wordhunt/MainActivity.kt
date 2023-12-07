package com.android.wordhunt

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var playButton: android.widget.Button
    private lateinit var binding: ActivityMainBinding

    class ActivityMainBinding {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wordApi = RetrofitHelper.getInstance().create(WordApi::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val result = wordApi.getWord()
            if (result != null)
            // Checking the results
                Log.d("user: ", result.body().toString())
        }

    }
}
