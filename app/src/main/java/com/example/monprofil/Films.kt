package com.example.monprofil

import android.os.Build
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
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import androidx.compose.material.*



@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Films(windowClass: WindowSizeClass, navController: NavController){

    val viewmodel : MainViewModel = viewModel()
    val movies by viewmodel.movies.collectAsState()

    var searchActive by remember {mutableStateOf(false)}
    var textSearched by remember {mutableStateOf("")}
    var searchView by remember {mutableStateOf(false)}


    Scaffold (
        topBar = {
            if(searchActive){
                SearchBar(modifier = Modifier.padding(15.dp),
                query = textSearched,
                onQueryChange = {textSearched = it },
                    onSearch = {viewmodel.getMovieSearched(textSearched)
                        searchActive = false
                               searchView = true},
                    active = true,
                    onActiveChange = {},
                    placeholder = {Text("Recherche Film")}
                ){}
            }else{
                TopAppBar(title = { Text("Recherche Film")},
                navigationIcon ={
                    IconButton(onClick = { navController.navigate("home") },
                    modifier = Modifier.padding(10.dp)) {
                        Icon(Icons.Filled.Home, "Home")
                    }

                },
                modifier= Modifier.fillMaxWidth(),
                actions= {
                    IconButton(onClick = { searchActive = true }) {
                        Icon(Icons.Filled.Search, "Search")
                    }
                    if(searchView) {
                        IconButton(
                            onClick = { viewmodel.getMovies()
                                searchView = false},
                            modifier = Modifier.padding(30.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_search_off_24),
                                contentDescription = "bouton fermeture recherche"
                            )
                        }
                    }
                },
                )
            }

        },
    bottomBar = {
       BottomNavigation {
           val navBackStackEntry by navController.currentBackStackEntryAsState()
           val currentDestination = navBackStackEntry?.destination
           BottomNavigationItem(
               icon = { Icon(
                   painter = painterResource(id = R.drawable.baseline_movie_24),
                   contentDescription = "bouton films"
               ) },
               label = { Text("Films") },
               selected = currentDestination?.hierarchy?.any { it.route == "films" } == true,
               onClick = {
                   navController.navigate("films") {
                       // Pop up to the start destination of the graph to
                       // avoid building up a large stack of destinations
                       // on the back stack as users select items
                       popUpTo(navController.graph.findStartDestination().id) {
                           saveState = true
                       }
                       // Avoid multiple copies of the same destination when
                       // reselecting the same item
                       launchSingleTop = true
                       // Restore state when reselecting a previously selected item
                       restoreState = true
                   }
               }
           )
           BottomNavigationItem(
               icon = { Icon(
                   painter = painterResource(id = R.drawable.baseline_live_tv_24),
                   contentDescription = "bouton series"
               ) },
               label = { Text("Series") },
               selected = currentDestination?.hierarchy?.any { it.route == "series" } == true,
               onClick = {
                   navController.navigate("series") {
                       // Pop up to the start destination of the graph to
                       // avoid building up a large stack of destinations
                       // on the back stack as users select items
                       popUpTo(navController.graph.findStartDestination().id) {
                           saveState = true
                       }
                       // Avoid multiple copies of the same destination when
                       // reselecting the same item
                       launchSingleTop = true
                       // Restore state when reselecting a previously selected item
                       restoreState = true
                   }
               }
           )
           BottomNavigationItem(
               icon = { Icon(
                   painter = painterResource(id = R.drawable.baseline_person_24),
                   contentDescription = "bouton acteurs"
               ) },
               label = { Text("Acteurs") },
               selected = currentDestination?.hierarchy?.any { it.route == "actors" } == true,
               onClick = {
                   navController.navigate("actors") {
                       // Pop up to the start destination of the graph to
                       // avoid building up a large stack of destinations
                       // on the back stack as users select items
                       popUpTo(navController.graph.findStartDestination().id) {
                           saveState = true
                       }
                       // Avoid multiple copies of the same destination when
                       // reselecting the same item
                       launchSingleTop = true
                       // Restore state when reselecting a previously selected item
                       restoreState = true

                   }
               }
           )
       }
    }
        )
        { Box(modifier = Modifier.padding(it)) {
        when(windowClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                LazyVerticalGrid(columns = GridCells.Fixed(2))
                /**, verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)**/
                {
                    items(movies){
                        Card( modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                            .clickable {navController.navigate("detailsfilm/{filmID}")}){
                            Column(horizontalAlignment = Alignment.CenterHorizontally){
                                if (it.poster_path != null){
                                    AsyncImage(
                                        model = "https://image.tmdb.org/t/p/original//"+it.poster_path,
                                        contentDescription = null,
                                    )
                                }
                                Spacer(Modifier.height(3.dp))
                                Row{
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        text = it.title,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .width(180.dp))
                                }

                                Spacer(Modifier.height(5.dp))
                                Row{
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        text = stringToDate(it.release_date) ,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                        fontWeight = FontWeight.Normal,
                                        modifier = Modifier
                                            .width(180.dp))
                                }

                            }
                        }
                    }

                }

            }
            else -> {
                LazyVerticalGrid(columns = GridCells.Fixed(3), verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ){
                    items(movies){
                        Card( modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                            .clickable {navController.navigate("detailsfilm/{filmID}")}){
                            Column(horizontalAlignment = Alignment.CenterHorizontally){
                                if (it.poster_path != null){
                                    AsyncImage(
                                        model = "https://image.tmdb.org/t/p/original//"+it.poster_path,
                                        contentDescription = null,
                                    )
                                }
                                Spacer(Modifier.height(3.dp))
                                Row{
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        text = it.title,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .width(180.dp))
                                }

                                Spacer(Modifier.height(5.dp))
                                Row{
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        text = stringToDate(it.release_date) ,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                        fontWeight = FontWeight.Normal,
                                        modifier = Modifier
                                            .width(180.dp))
                                }


                            }
                        }
                    }
                }

            }
        }
    }

    }




}






