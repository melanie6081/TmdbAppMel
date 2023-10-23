package com.example.monprofil

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.monprofil.ui.theme.MonProfilTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val navController = rememberNavController()


            MonProfilTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") { Screen(windowSizeClass, navController) }
                        composable("films") { Films(windowSizeClass, navController) }
                        composable("series") { Series(windowSizeClass, navController) }
                        composable("actors") { Actors(windowSizeClass, navController) }
                        /**composable("SearchSreen/{search}") { backStackEntry ->
                            val search = backStackEntry.arguments?.getString("search") ?: ""
                            SearchScreen(windowSizeClass, navController, search) }**/
                        composable("detailsfilm/{filmID}"){ backStackEntry ->
                            val filmID = backStackEntry.arguments?.getString("filmID")?:""
                            DetailsFilm(windowSizeClass, navController, filmID)}
                        composable("detailsserie/{serieID}"){backStackEntry ->
                            val serieID = backStackEntry.arguments?.getString("serieID")?:""
                            DetailsSerie(windowSizeClass, navController, serieID)}
                        composable("detailsacteur/{acteurID}"){ backStackEntry ->
                            val acteurID = backStackEntry.arguments?.getString("acteurID")?:""
                            DetailsActeur(windowSizeClass, navController, acteurID)}

                    }
                }
            }
        }
    }
}