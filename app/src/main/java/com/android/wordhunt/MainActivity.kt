package com.android.wordhunt

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.wordhunt.GameScreen.Backend.gridButtonHandler
import com.android.wordhunt.GameScreen.Backend.gridInitializer
import com.android.wordhunt.ui.theme.MyApplicationTheme
import com.google.firebase.inappmessaging.model.Button

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_menu)
    }
}
