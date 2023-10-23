package com.example.monprofil

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberImagePainter
import java.util.Locale
import java.util.regex.Pattern

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsFilm(windowClass: WindowSizeClass, navController: NavController, filmID: String){

    val viewmodel : MainViewModel = viewModel()
    val movie by viewmodel.movie.collectAsState()

    LaunchedEffect(true ) {
        Log.v("xxx", filmID)
        viewmodel.getMovieDetails(filmID)
    }

    Scaffold (
        topBar = {
                TopAppBar(title = { Text("Détails Film") },
                    navigationIcon ={
                        IconButton(
                            onClick = { navController.navigate("films") },
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                                contentDescription = "bouton retour films"
                            )
                        }

                    },
                    modifier= Modifier.fillMaxWidth(),
                )

        }
    ){
        Box(modifier = Modifier.padding(it)){
            LazyColumn() {
                // Titre + Image de fond du film
                item {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = movie.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                        )
                        Image(
                            painter = rememberImagePainter(
                                data = "https://image.tmdb.org/t/p/w1280" + movie.backdrop_path,
                                builder = {
                                    crossfade(true)
                                    size(600, 600)
                                }),
                            contentDescription = "Image film ${movie.title}",
                            Modifier
                                .padding(start = 15.dp, end = 15.dp)
                                .fillMaxWidth()
                        )
                    }
                }
                // Affiche + Date de sortie + Genre
                item {
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = "https://image.tmdb.org/t/p/w1280" + movie.poster_path,
                                builder = {
                                    crossfade(true)
                                    size(400, 400)
                                }),
                            contentDescription = "Image film ${movie.title}",
                            Modifier.padding(start = 25.dp, end = 10.dp, top = 5.dp)
                        )
                        Column(
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(start = 20.dp, end = 15.dp)
                        ) {
                            var checkReleaseDate =false
                            if(movie.release_date != null){
                                checkReleaseDate = Pattern.matches("^\\d{4}-\\d{2}-\\d{2}\$\n", movie.release_date)
                            }
                            if(movie.release_date != "" && checkReleaseDate){
                                Text(
                                    text = "date de sortie du film",
                                    //text = stringToDate(movie.release_date),
                                    color = Color.Gray,
                                    modifier = Modifier.padding(top = 15.dp),
                                    fontSize = 15.sp
                                )
                            }

                            Text(
                                text = getGenres(movie.genres),
                                fontStyle = FontStyle.Italic,
                                modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                            )
                        }
                    }
                }
                // Synopsis
                item {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.padding(start = 10.dp)
                    ) {
                        Text(
                            text = "Synopsis",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                        )
                        Text(
                            text = movie.overview,
                            modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                        )
                    }
                }
                if(movie.credits.cast.isNotEmpty()){
                    item {
                        Text(
                            text = "Têtes d'affiches",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                        )
                    }
                    items(movie.credits.cast){ cast ->
                        FloatingActionButton(
                            onClick = { navController.navigate("detailsacteur/${cast.id}") },
                            modifier = Modifier.padding(20.dp),
                            containerColor = Color.White,
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Image(
                                        painter = rememberImagePainter(
                                            data = "https://image.tmdb.org/t/p/w780" + cast.profile_path,
                                            builder = {
                                                crossfade(true)
                                                size(
                                                    350,
                                                    400
                                                )
                                            }),
                                        contentDescription = "Image film ${cast.name}",
                                        Modifier.padding(start = 5.dp, end = 5.dp)
                                    )
                                    Text(
                                        text = cast.name,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                        fontSize = 20.sp,
                                        modifier = Modifier.padding(top = 5.dp)
                                    )
                                    Text(
                                        text = cast.character,
                                        color = Color.Black,
                                        fontSize = 18.sp,
                                        modifier = Modifier.padding(top = 15.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }

    }

}

fun getGenres(genres: List<Genre>): String {
    var genresString = ""
    for (genre in genres) {
        genresString += genre.name + " & "
    }
    return genresString.dropLast(2)
}