package com.example.moviecatalogue.ui.searchtv

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

class SearchTvShowViewModel @Inject constructor(private val movieServices: MovieServices) :
    BaseViewModel() {

    private var coroutineScope = CoroutineScope(Dispatchers.Main)

    private var _searchTvShow = MutableLiveData<List<Movie>>()
    val searchTvShow: LiveData<List<Movie>>
        get() = _searchTvShow

    var query: String? = null

    init {
        getSearchTvShow()
    }

    private fun getSearchTvShow() {
        coroutineScope.launch {
            try {
                val getSearchTvShow = movieServices.getSearchTvAsync(query = query).await().results
                _searchTvShow.value = getSearchTvShow
            } catch (e: NetworkErrorException) {
                e.printStackTrace()
            }
        }
    }
}
