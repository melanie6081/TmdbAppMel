package com.example.monprofil

class TmdbMovieResult(
    var page: Int = 0,
    val results: List<TmdbMovie> = listOf())

class TmdbMovie(
    var overview: String = "",
    val release_date: String = "",
    val id: String = "",
    val title: String = "",
    val original_title: String = "",
    val backdrop_path: String? = "",
    val genre_ids: List<Int> = listOf(),
    val poster_path: String? = "")

data class Movies(
    val page: Int,
    val results: List<ResultMovies>,
    val total_pages: Int,
    val total_results: Int
)

data class ResultMovies(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class TmdbMovieDetails(
    //val adult: Boolean,
    val backdrop_path: String ="",
    //val belongs_to_collection: Any,
    val budget: Int = 0,
    val credits: Credits = Credits(),
    val genres: List<Genre> = listOf(),
    //val homepage: String,
    val id: Int = 0,
    //val imdb_id: String,
    val original_language: String = "",
    val original_title: String="",
    val overview: String="",
    val popularity: Double=0.0,
    val poster_path: String="",
    //val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry> = listOf(),
    val release_date: String ="",
    val revenue: Int = 0,
    val runtime: Int = 0,
    //val spoken_languages: List<SpokenLanguage>,
    //val status: String,
    val tagline: String ="",
    val title: String ="",
    //val video: Boolean,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)

data class Credits(
    val cast: List<Cast> = listOf(),
    //val crew: List<Crew>
)

data class Genre(
    val id: Int = 0,
    val name: String = ""
)

data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

data class ProductionCountry(
    //val iso_3166_1: String,
    val name: String =""
)

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)

data class Cast(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)

data class Crew(
    val adult: Boolean,
    val credit_id: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)
data class TmdbActorDetails(
   // val adult: Boolean,
   // val also_known_as: List<String>,
    val biography: String = "",
    val birthday: String = "",
    //val deathday: String = "",
    val gender: Int = 0,
    //val homepage: Any,
    val id: Int = 0,
    //val imdb_id: String,
    val known_for_department: String = "",
    val name: String = "",
    val place_of_birth: String = "",
    val popularity: Double = 0.0,
    val profile_path: String = ""
)

data class TmdbActorResult(
    val page: Int,
    val results: List<Actor>,
    val total_pages: Int,
    val total_results: Int
)

data class Actor(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for: List<KnownFor>,
    val known_for_department: String,
    val media_type: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)

data class KnownFor(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class TmdbSeriesResult(
    val page: Int,
    val results: List<Serie>,
    val total_pages: Int,
    val total_results: Int
)

data class Serie(
    //val adult: Boolean,
    val backdrop_path: String = "",
    val first_air_date: String = "",
    val genre_ids: List<Int> = listOf(),
    val id: Int = 0,
    val media_type: String = "",
    val name: String = "",
    val origin_country: List<String> = listOf(),
    val original_language: String = "",
    val original_name: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)