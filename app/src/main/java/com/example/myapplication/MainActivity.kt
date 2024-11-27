package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val windowSizeClass = calculateWindowSizeClass(this)
                val viewModel: MainViewModel by viewModels()

                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "Profil"
                    ) {
                        composable("Profil") {
                            Profil(
                                name = "Nabil Al Bisous",
                                modifier = Modifier.padding(innerPadding),
                                onStartClicked = { navController.navigate("Films") }
                            )
                        }
                        composable("Films") {
                            Films(navController, windowSizeClass, viewModel)
                        }
                        composable("DetailsFilm/{filmID}") { backStackEntry ->
                            val filmID = backStackEntry.arguments?.getString("filmID") ?: ""
                            DetailsFilm(navController, windowSizeClass, viewModel, filmID)
                        }
                        composable("Séries") {
                            Séries(navController, windowSizeClass, viewModel)
                        }
                        composable("DetailsSerie/{serieID}") { backStackEntry ->
                            val serieID = backStackEntry.arguments?.getString("serieID") ?: ""
                            DetailsSerie(navController, windowSizeClass, viewModel, serieID)
                        }
                        composable("Acteurs") {
                            Acteurs(navController, windowSizeClass, viewModel)
                        }
                        composable("DetailsActeur/{acteurID}") { backStackEntry ->
                            val acteurID = backStackEntry.arguments?.getString("acteurID") ?: ""
                            DetailsActeur(navController, windowSizeClass, viewModel, acteurID)
                        }
                    }
                }
            }
        }
    }
}
