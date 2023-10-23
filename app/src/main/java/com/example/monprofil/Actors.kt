package com.example.monprofil

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
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Actors(windowClass: WindowSizeClass, navController: NavController){

    val viewmodel : MainViewModel = viewModel()
    val acteurs by viewmodel.acteurs.collectAsState()

    var searchActive by remember { mutableStateOf(false) }
    var textSearched by remember { mutableStateOf("") }
    var searchView by remember { mutableStateOf(false) }

    Scaffold (
        topBar = {
            if(searchActive){
                SearchBar(modifier = Modifier.padding(15.dp),
                    query = textSearched,
                    onQueryChange = {textSearched = it },
                    onSearch = {viewmodel.getActeursSearched(textSearched)
                        searchActive = false
                        searchView = true},
                    active = true,
                    onActiveChange = {},
                    placeholder = {Text("Recherche Acteur")}
                ){}
            }else{
                TopAppBar(title = { Text("Recherche Acteur")},
                    navigationIcon ={
                        androidx.compose.material3.IconButton(
                            onClick = { navController.navigate("home") },
                            modifier = Modifier.padding(10.dp)
                        ) {
                            androidx.compose.material3.Icon(Icons.Filled.Home, "Home")
                        }

                    },
                    modifier= Modifier.fillMaxWidth(),
                    actions= {
                        androidx.compose.material3.IconButton(onClick = { searchActive = true }) {
                            androidx.compose.material3.Icon(Icons.Filled.Search, "Search")
                        }
                        if(searchView) {
                            androidx.compose.material3.IconButton(
                                onClick = {
                                    viewmodel.getActeurs()
                                    searchView = false
                                },
                                modifier = Modifier.padding(30.dp)
                            ) {
                                androidx.compose.material3.Icon(
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
    {
        Box(modifier = Modifier.padding(it)) {
        when(windowClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {

                LazyVerticalGrid(columns = GridCells.Fixed(2))

                {
                    items(acteurs){
                        Card( modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                            .clickable { navController.navigate("detailsacteur/"+ it.id) }){
                            Column(horizontalAlignment = Alignment.CenterHorizontally){
                                if (it.profile_path != null){
                                    AsyncImage(
                                        model = "https://image.tmdb.org/t/p/original//"+it.profile_path,
                                        contentDescription = null,
                                    )
                                }
                                Spacer(Modifier.height(3.dp))
                                Row{
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        text = it.name,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                        fontWeight = FontWeight.Bold,
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
                    items(acteurs){
                        Card( modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                            .clickable {navController.navigate("detailsacteur/"+ it.id) }){
                            Column(horizontalAlignment = Alignment.CenterHorizontally){
                                if (it.profile_path != null){
                                    AsyncImage(
                                        model = "https://image.tmdb.org/t/p/original//"+it.profile_path,
                                        contentDescription = null,
                                    )
                                }
                                Spacer(Modifier.height(3.dp))
                                Row{
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        text = it.name,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                        fontWeight = FontWeight.Bold,
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