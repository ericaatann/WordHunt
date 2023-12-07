package com.android.wordhunt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var playButton: android.widget.Button
    private lateinit var binding: ActivityMainBinding

    class ActivityMainBinding {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
