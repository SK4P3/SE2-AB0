package com.example.se2_ab0

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.se2_ab0.ui.theme.SE2_AB0Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SE2_AB0Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var text = "";


    Column(modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(16.dp))

        Text("Geben Sie Ihre Matrikelnummer ein")

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Matrikelnummer") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
          /* TODO: Add TCP Call */
        }) {
            Text("Submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SE2_AB0Theme {
        Greeting("Android")
    }
}