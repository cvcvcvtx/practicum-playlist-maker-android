package com.practicum.playlist_maker_android_dmitrikovvitaly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import screens.DrawMainScreen
import screens.DrawSearchScreen
import screens.DrawSettingsScreen


enum class Routes(val route: String){
    MAIN("main"),
    SEARCH("search"),
    SETTINGS("settings"),

}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            PlaylistHost(
                navController = rememberNavController(),
                startDestination = Routes.MAIN.route,
                modifier = Modifier.fillMaxSize()
            )

        }
    }





    @Composable
    fun PlaylistHost(
        modifier: Modifier = Modifier,
        startDestination: String = Routes.MAIN.route,
        navController: NavHostController,
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
        ) {
            composable(route = Routes.MAIN.route) {
                DrawMainScreen(
                    navigateToSearch = { navController.navigate(Routes.SEARCH.route) },
                    navigateToPlaylists = {  },
                    navigateToFavourites = {  },
                    navigateToSettings = { navController.navigate(Routes.SETTINGS.route) },
                )
            }


            composable(route = Routes.SEARCH.route) {
                DrawSearchScreen(
                    navController = navController
                )
            }


            composable(route = Routes.SETTINGS.route) {
                DrawSettingsScreen(

                )
            }



        }
    }













}

