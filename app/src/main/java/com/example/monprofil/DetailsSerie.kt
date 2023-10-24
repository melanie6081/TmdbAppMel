package com.example.monprofil

import android.util.Log
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsSerie(windowClass: WindowSizeClass, navController: NavController, serieID: String){

    val viewmodel : MainViewModel = viewModel()
    val serie by viewmodel.serie.collectAsState()

    LaunchedEffect(true ) {
        Log.v("xxx", serieID)
        viewmodel.getSerieDetails(serieID)
    }

    Scaffold (
        topBar = {
            TopAppBar(title = { Text("Détails Série") },
                navigationIcon ={
                    IconButton(
                        onClick = { navController.navigate("series") },
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                            contentDescription = "bouton retour series"
                        )
                    }

                },
                modifier= Modifier.fillMaxWidth(),
            )

        }
    ){
        Box(modifier = Modifier.padding(it)){
            LazyColumn() {
           
                item {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = serie.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                        )
                        Image(
                            painter = rememberImagePainter(
                                data = "https://image.tmdb.org/t/p/w1280" + serie.backdrop_path,
                                builder = {
                                    crossfade(true)
                                    size(600, 600)
                                }),
                            contentDescription = "Image série ${serie.name}",
                            Modifier
                                .padding(start = 15.dp, end = 15.dp)
                                .fillMaxWidth()
                        )
                    }
                }
              
                item {
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = "https://image.tmdb.org/t/p/w1280" + serie.poster_path,
                                builder = {
                                    crossfade(true)
                                    size(400, 400)
                                }),
                            contentDescription = "Image série ${serie.name}",
                            Modifier.padding(start = 25.dp, end = 10.dp, top = 5.dp)
                        )
                        Column(
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(start = 20.dp, end = 15.dp)
                        ) {
                            var checkDate =false
                            if(serie.first_air_date != null){
                                checkDate = Pattern.matches("^\\d{4}-\\d{2}-\\d{2}\$\n", serie.first_air_date)
                            }
                            if(checkDate){
                                Text(
                                    //text = "date de sortie du film",
                                    text = stringToDate(serie.first_air_date),
                                    color = Color.Gray,
                                    modifier = Modifier.padding(top = 15.dp),
                                    fontSize = 15.sp
                                )
                            }

                            Text(
                                text = getGenres(serie.genres),
                                fontStyle = FontStyle.Italic,
                                modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                            )
                        }
                    }
                }
       
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
                            text = serie.overview,
                            modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                        )
                    }
                }
       
                if(serie.credits.cast.isNotEmpty()){
                    item {
                        Text(
                            text = "Têtes d'affiches",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                        )
                    }
                    items(serie.credits.cast){ cast ->
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
                                        contentDescription = "Image série ${cast.name}",
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
