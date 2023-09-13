package com.bupware.ricardogdztest

import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.bupware.ricardogdztest.core.navigation.Destinations
import com.bupware.ricardogdztest.core.navigation.NavigationHost
import com.bupware.ricardogdztest.ui.theme.RicardogdzTestTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RicardogdzTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LaunchApp()
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun LaunchApp(){
    NavigationHost(navController = rememberNavController(), startDestination = Destinations.HomeScreen.ruta)
}

@HiltAndroidApp
class RicardogdzTest: Application(){}