package com.example.monprofil

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import java.util.Locale
import java.util.regex.Pattern

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsActeur(windowClass: WindowSizeClass, navController: NavController, acteurID: String){

    val viewmodel : MainViewModel = viewModel()
    val acteur by viewmodel.acteur.collectAsState()

    LaunchedEffect(true ) {
        Log.v("xxx", acteurID)
        viewmodel.getActorDetails(acteurID)
    }

    Scaffold (
        topBar = {
            TopAppBar(title = { Text("Détails Acteur") },
                navigationIcon ={
                    IconButton(
                        onClick = { navController.popBackStack()},
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                            contentDescription = "bouton retour acteurs"
                        )
                    }

                },
                modifier= Modifier.fillMaxWidth(),
            )

        }
    ){
        Box(modifier = Modifier.padding(it)){

                when(windowClass.widthSizeClass) {
                    WindowWidthSizeClass.Compact -> {

                        Column(verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.verticalScroll(rememberScrollState())) {
                            
                            Column(
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    text = acteur.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 30.sp,
                                    modifier = Modifier
                                        .padding(top = 10.dp, bottom = 10.dp)
                                        .align(Alignment.CenterHorizontally)
                                )
                            }

                            Image(
                                painter = rememberImagePainter(
                                    data = "https://image.tmdb.org/t/p/h632" + acteur.profile_path,
                                    builder = {
                                        crossfade(true)
                                        size(500, 500)
                                    }),
                                contentDescription = "Image film ${acteur.name}",
                                Modifier
                                    .padding(start = 15.dp, end = 15.dp)
                            )

                
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                if (acteur.gender == 1) {
                                    Text(
                                        text = "Sexe : Femme",
                                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                                    )
                                } else {
                                    Text(
                                        text = "Sexe : Homme",
                                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                                    )
                                }
                                if(acteur.known_for_department != ""){
                                    Text(
                                        text = "Métier : " + acteur.known_for_department,
                                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                                    )
                                }
                                var checkBirthday = false
                                if(acteur.birthday != null){
                                    checkBirthday = Pattern.matches("^\\d{4}-\\d{2}-\\d{2}\$\n", acteur.birthday)
                                }
                                if(acteur.place_of_birth != "" && checkBirthday){
                                    Text(
                                        text = "Lieu de naissance : " + acteur.place_of_birth,
                                        fontStyle = FontStyle.Italic,
                                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                                    )
                                    Text(
                                        text = stringToDate(acteur.birthday),
                                        modifier = Modifier.padding(top = 15.dp),
                                        fontSize = 15.sp
                                    )
                                }
                            }
                            
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(start = 10.dp)
                            ) {
                                if(acteur.biography != ""){
                                    Text(
                                        text = "Biographie",
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                                    )
                                    Text(
                                        text = acteur.biography,
                                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                                    )
                                }
                            }
                        }
                    }
                    else -> {
                        Column(verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.verticalScroll(rememberScrollState())) {
                            
                            Column(
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    text = acteur.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 30.sp,
                                    modifier = Modifier
                                        .padding(top = 10.dp, bottom = 10.dp)
                                        .align(Alignment.CenterHorizontally)
                                )
                            }

                            Image(
                                painter = rememberImagePainter(
                                    data = "https://image.tmdb.org/t/p/h632" + acteur.profile_path,
                                    builder = {
                                        crossfade(true)
                                        size(500, 500)
                                    }),
                                contentDescription = "Image film ${acteur.name}",
                                Modifier
                                    .padding(start = 15.dp, end = 15.dp)
                            )

                         
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                if (acteur.gender == 1) {
                                    Text(
                                        text = "Sexe : Femme",
                                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                                    )
                                } else {
                                    Text(
                                        text = "Sexe : Homme",
                                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                                    )
                                }
                                if(acteur.known_for_department != ""){
                                    Text(
                                        text = "Métier : " + acteur.known_for_department,
                                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                                    )
                                }
                                var checkBirthday = false
                                if(acteur.birthday != null){
                                    checkBirthday = Pattern.matches("^\\d{4}-\\d{2}-\\d{2}\$\n", acteur.birthday)
                                }
                                if(acteur.place_of_birth != "" && checkBirthday){
                                    Text(
                                        text = "Lieu de naissance : " + acteur.place_of_birth,
                                        fontStyle = FontStyle.Italic,
                                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                                    )
                                    Text(
                                        text = stringToDate(acteur.birthday),
                                        modifier = Modifier.padding(top = 15.dp),
                                        fontSize = 15.sp
                                    )
                                }

                            }
                      
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(start = 10.dp)
                            ) {
                                if(acteur.biography != ""){
                                    Text(
                                        text = "Biographie",
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                                    )
                                    Text(
                                        text = acteur.biography,
                                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                                    )
                                }
                            }
                        }

                    }
                }

            }



    }

}
