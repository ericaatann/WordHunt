package com.android.wordhunt

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.wordhunt.databinding.ActivityMainBinding

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
