package com.bupware.ricardogdztest.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bupware.ricardogdztest.core.navigation.Destinations
import com.bupware.ricardogdztest.screens.CharacterFile
import com.bupware.ricardogdztest.screens.HomeScreen

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavigationHost (navController: NavHostController,startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    )
    {
        composable(Destinations.HomeScreen.ruta){
            HomeScreen(navController)
        }

        composable("${Destinations.CharacterFile.ruta}/{characterID}",arguments = listOf(navArgument("characterID"){type = NavType.IntType})){ backStackEntry ->
            CharacterFile(backStackEntry.arguments!!.getInt("characterID"))
        }

    }
}









