package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import coil.compose.rememberImagePainter

@Composable
fun Acteurs(navController: NavController,
            windowClass: WindowSizeClass,
            viewModel: MainViewModel
) {
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Scaffold(
                topBar = {
                    BarreRecherche(
                        viewModel,
                        onSearchClick = {
                            viewModel.getActeursRecherche(query = it)
                        })
                },

                bottomBar = {
                    BarreNavigation(navController)
                },
                content = {
                    Box(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxWidth()
                    ) {
                        ListeActeursPopulaire(navController, windowClass, viewModel)
                    }
                }
            )
        } else -> {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            BarreNavigationPaysage(navController)
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomStart
            ) {
                ListeActeursPopulaire(navController, windowClass, viewModel, nbColonne = 3)

                BarreRecherchePaysage(viewModel,
                    onSearchClick = {
                        viewModel.getActeursRecherche(query = it)
                    })
            }
        }
    }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListeActeursPopulaire(navController: NavController,
                          windowClass: WindowSizeClass,
                          viewmodel: MainViewModel,
                          nbColonne: Int=2) {

    val acteurs by viewmodel.acteurs.collectAsState()
    LaunchedEffect(true) {
        viewmodel.getActeursPopulaires()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(nbColonne),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(acteurs) { acteur ->
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp,
                ),
                onClick = {
                    //  navController.navigate("DetailsActeur/${acteur.id}")
                },
                modifier = Modifier
                    .width(300.dp)
                    .height(360.dp)
                    .padding(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF142949),
                )
            ){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                    //.height(200.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = "https://image.tmdb.org/t/p/w780" + acteur.profile_path,
                                builder = {
                                    crossfade(true)
                                }
                            ),
                            contentDescription = "Image du film" + acteur.name,
                            modifier = Modifier
                                .width(200.dp)
                                .height(300.dp)
                                .padding(
                                    start = 8.dp,
                                    top = 8.dp,
                                    end = 8.dp,
                                    bottom = 0.dp
                                )
                        )
                        Text(
                            text = acteur.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                    }
                }

            }
        }
    }
}
