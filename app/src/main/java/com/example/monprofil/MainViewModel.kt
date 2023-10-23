package com.example.monprofil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(TmdbAPI::class.java)

    val movies = MutableStateFlow<List<TmdbMovie>>(listOf())
    val movie = MutableStateFlow<TmdbMovieDetails>(TmdbMovieDetails())
    val series = MutableStateFlow<List<Serie>>(listOf())
    val serie = MutableStateFlow<Serie>(Serie())
    val acteurs = MutableStateFlow<List<Actor>>(listOf())
    val acteur = MutableStateFlow<TmdbActorDetails>(TmdbActorDetails())

    fun getMovies(){
        viewModelScope.launch{
            movies.value = api.lastmovies("d07b9d246446703dc6646c91bea54ffe").results
        }
    }
    fun getMovieSearched(query:String){
        viewModelScope.launch {
            movies.value = api.moviesearched(query, "d07b9d246446703dc6646c91bea54ffe", "fr").results
        }
    }
    fun getMovieDetails(filmID: String){
        viewModelScope.launch{
            movie.value = api.moviedetails(filmID, "d07b9d246446703dc6646c91bea54ffe","fr")
        }
    }


    fun getSeries(){
        viewModelScope.launch{
            series.value = api.lastseries("d07b9d246446703dc6646c91bea54ffe").results
        }
    }
    fun getSerieSearched(query:String){
        viewModelScope.launch {
            series.value = api.seriesearched(query, "d07b9d246446703dc6646c91bea54ffe", "fr").results
        }
    }
    fun getSerieDetails(serieID: String){
        viewModelScope.launch{
            serie.value = api.seriedetails(serieID, "d07b9d246446703dc6646c91bea54ffe","fr")
        }
    }


    fun getActeurs(){
        viewModelScope.launch{
            acteurs.value = api.lastactors("d07b9d246446703dc6646c91bea54ffe").results
        }
    }

    fun getActeursSearched(query:String){
        viewModelScope.launch {
            acteurs.value = api.actorsearched(query, "d07b9d246446703dc6646c91bea54ffe", "fr").results
        }
    }
    fun getActorDetails(acteurID: String){
        viewModelScope.launch{
            acteur.value = api.actordetails(acteurID, "d07b9d246446703dc6646c91bea54ffe","fr")
        }
    }

    init{
        getMovies()
        getSeries()
        getActeurs()
    }
}