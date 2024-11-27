package com.example.myapplication


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberImagePainter


@Composable
fun DetailsActeur(navController: NavController,
                  windowClass: WindowSizeClass,
                  viewModel: MainViewModel,
                  acteurid: String
) {
    LaunchedEffect(true) {
        viewModel.getDetailActeur(acteurid)
    }
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Box(
                modifier = Modifier
                    .background(Color(0xFF142949)),
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item(span = {
                        GridItemSpan(2)
                    }) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            ProfilActeur(viewModel, acteurid)
                        }
                    }
                    item(span = {
                        GridItemSpan(2)
                    }) {
                        Row(
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            PresentationFilm(viewModel, acteurid)
                        }
                    }
                }
            }
        }

        else -> {
            Box(
                modifier = Modifier
                    .background(Color(0xFF142949)),
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item(span = {
                        GridItemSpan(4)
                    }) {
                        ProfilActeur(viewModel, acteurid)
                    }
                    item(span = {
                        GridItemSpan(4)
                    }) {
                        Row(
                            //Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Center
                        ) {

                        }
                    }
                    item(span = {
                        GridItemSpan(4)
                    }) {
                        Filmographie()
                    }
                }
            }
        }
    }
}
@Composable
fun ProfilActeur(viewModel: MainViewModel, acteurid: String){
    val acteur by viewModel.acteur.collectAsState()
    LaunchedEffect(true) {
        viewModel.getDetailActeur(acteurid)
    }
    Image(
        painter = rememberImagePainter(
            data = "https://image.tmdb.org/t/p/w500" + acteur.profile_path,
            builder = {
                crossfade(true)
            }
        ),
        contentDescription = "Image du film" + acteur.name,
        modifier = Modifier
            .fillMaxWidth()
            .alpha(0.8f)
            .height(200.dp)

    )
    Text(
        text = acteur.name,
        style = MaterialTheme.typography.titleLarge,
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textAlign = TextAlign.Center,
    )

}





