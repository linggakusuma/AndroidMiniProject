package com.example.moviecatalogue.ui.search

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

class SearchViewModel @Inject constructor(private val movieServices: MovieServices) :
    BaseViewModel() {

    private var coroutineScope = CoroutineScope(Dispatchers.Main)

    private var _search = MutableLiveData<List<Movie>>()
    val search: LiveData<List<Movie>>
        get() = _search

    var query: String? = null

    init {
        getSearchMovie()
    }

    private fun getSearchMovie() {
        coroutineScope.launch {
            val getSearchMovieDeferred = movieServices.getSearchMovieAsync(query = query)
            try {
                val listSearchMovie = getSearchMovieDeferred.await().results
                _search.value = listSearchMovie

            } catch (e: NetworkErrorException) {
                e.printStackTrace()
            }
        }
    }
}
