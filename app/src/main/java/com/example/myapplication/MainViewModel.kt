package com.example.myapplication


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    val movies = MutableStateFlow<List<Movie>>(listOf())
    val series = MutableStateFlow<List<Serie>>(listOf())
    val film = MutableStateFlow<FilmDetail>(FilmDetail())
    val serie = MutableStateFlow<SerieDetail>(SerieDetail())
    val acteurs = MutableStateFlow<List<Acteur>>(listOf())
    val acteur = MutableStateFlow<ActeurDetail>(ActeurDetail())

    //clé de l'API TMTB
    val apikey = "2a30d7eb7bc26b1eb73f75ff56d57059"


    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)

    fun getFilmsInitiaux() {
        viewModelScope.launch {
            movies.value = service.getFilmAffiche(apikey,"fr").results //résult quand il s'agit d'une liste
        }
    }
    fun getFilmsRecherche(query: String) {
        viewModelScope.launch {
            movies.value = service.getFilmRecherche(query, apikey,"fr").results
        }
    }
    fun getDetailFilm(movieid: String) {
        viewModelScope.launch {
            film.value = service.getFilmDetail(movieid, apikey, "fr")
        }
    }
    fun getSeriesInitiales() {
        viewModelScope.launch {
            series.value = service.getSerieAffiche(apikey, "fr").results
        }
    }
    fun getSeriesRecherche(query: String) {
        viewModelScope.launch {
            series.value = service.getSerieRecherche(query, apikey, "fr").results
        }
    }
    fun getDetailSerie(serieid: String) {
        viewModelScope.launch {
            serie.value = service.getSerieDetail(serieid, apikey, "fr")
        }
    }
    fun getActeursPopulaires() {
        viewModelScope.launch {
            acteurs.value = service.getActeurPopulaire(apikey, "fr").results
        }
    }
    fun getActeursRecherche(query: String) {
        viewModelScope.launch {
            acteurs.value = service.getActeurRecherche(query, apikey, "fr").results
        }
    }
    fun getDetailActeur(acteurid: String) {
        viewModelScope.launch {
            acteur.value = service.getActeurDetail(acteurid, apikey, "fr")
        }
    }

}