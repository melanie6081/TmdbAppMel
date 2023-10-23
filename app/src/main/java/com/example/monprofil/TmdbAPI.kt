package com.example.monprofil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface TmdbAPI {
    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") api_key: String): TmdbMovieResult

    @GET("search/movie")
    suspend fun moviesearched(@Query("query") query: String, @Query("api_key")api_key: String, @Query("language")language: String): TmdbMovieResult

    @GET("movie/{id}?append_to_response=credits")
    suspend fun moviedetails(@Path("id")id:String, @Query("api_key")api_key: String, @Query("language")language: String): TmdbMovieDetails


    @GET("trending/tv/week")
    suspend fun lastseries(@Query("api_key") api_key: String): TmdbSeriesResult

    @GET("search/tv")
    suspend fun seriesearched(@Query("query") query: String, @Query("api_key")api_key: String, @Query("language")language: String): TmdbSeriesResult

    @GET("tv/{id}?append_to_response=credits")
    suspend fun seriedetails(@Path("id")id:String, @Query("api_key")api_key: String, @Query("language")language: String): TmdbSerieDetail


    @GET("trending/person/week")
    suspend fun lastactors(@Query("api_key") api_key: String): TmdbActorResult

    @GET("search/person")
    suspend fun actorsearched(@Query("query") query: String, @Query("api_key")api_key: String, @Query("language")language: String): TmdbActorResult

    @GET("person/{id}?append_to_response=credits")
    suspend fun actordetails(@Path("id")id:String, @Query("api_key")api_key: String, @Query("language")language: String): TmdbActorDetails
}



