package com.example.se2_ab0

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.se2_ab0.ui.theme.SE2_AB0Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SE2_AB0Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainView("Android")
                }
            }
        }
    }
}

@Composable
fun MainView(name: String, modifier: Modifier = Modifier) {
    var matNrInput by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf<String?>(null) }
    var serverResponse by remember { mutableStateOf<String?>(null) }

    Column(modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(16.dp))

        Text("Geben Sie Ihre Matrikelnummer ein")

        OutlinedTextField(
            value = matNrInput,
            onValueChange = { matNrInput = it },
            label = { Text("Matrikelnummer") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { sendToServer(matNrInput) { response -> serverResponse = response } }) {
            Text("How long have I studied?")
        }

        Spacer(modifier = Modifier.height(16.dp))

        serverResponse?.let {
            Text(it)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { resultText = findCommonDivisors(matNrInput)} ) {
            Text("Teiler prüfen")
        }

        resultText?.let {
            Text(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SE2_AB0Theme {
        MainView("Android")
    }
}