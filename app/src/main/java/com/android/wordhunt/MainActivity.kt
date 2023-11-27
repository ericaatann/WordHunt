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
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }

        val gridButtonHandler = gridButtonHandler()

        val button00: View = findViewById(R.id.button00)
        button00.setOnClickListener(gridButtonHandler.onClickListener(0, 0))

        val button01: View = findViewById(R.id.button01)
        val button02: View = findViewById(R.id.button02)
        val button03: View = findViewById(R.id.button03)

        val button10: View = findViewById(R.id.button10)
        val button11: View = findViewById(R.id.button11)
        val button12: View = findViewById(R.id.button12)
        val button13: View = findViewById(R.id.button13)

        val button20: View = findViewById(R.id.button20)
        val button21: View = findViewById(R.id.button21)
        val button22: View = findViewById(R.id.button22)
        val button23: View = findViewById(R.id.button23)

        val button30: View = findViewById(R.id.button30)
        val button31: View = findViewById(R.id.button31)
        val button32: View = findViewById(R.id.button32)
        val button33: View = findViewById(R.id.button33)


    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}