package com.example.moviecatalogue.ui.notification

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.remote.response.Movie
import com.example.moviecatalogue.data.remote.services.MovieServices
import com.example.moviecatalogue.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieNotificationViewModel @Inject constructor(private val movieServices: MovieServices) :
    BaseViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private var _movie = MutableLiveData<List<Movie>>()
    val movie: LiveData<List<Movie>>
        get() = _movie

    init {
        getMovieNotification()
    }

    private fun getMovieNotification() {
        coroutineScope.launch {
            try {
                val getMovieNotification = movieServices.getMovieNowPlayingAsync().await().results
                _movie.value = getMovieNotification
            } catch (e: NetworkErrorException) {
                e.printStackTrace()
            }
        }
    }
}