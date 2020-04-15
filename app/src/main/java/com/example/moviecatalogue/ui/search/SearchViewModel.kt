package com.example.moviecatalogue.ui.search

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.remote.response.Movie
import com.example.moviecatalogue.data.remote.services.MovieServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val movieServices: MovieServices) : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _search = MutableLiveData<List<Movie>>()
    val search: LiveData<List<Movie>>
        get() = _search

    var query: String? = null

    init {
        getSearchMovie()
    }

    private fun getSearchMovie() {
        coroutineScope.launch {
            val getSearchMovieDeferred = movieServices.getSearchAsync(query = query)
            try {
                val listSearchMovie = getSearchMovieDeferred.await().results
                _search.value = listSearchMovie

            } catch (e: NetworkErrorException) {
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
