package com.example.moviecatalogue.data.source.movie

import androidx.paging.PageKeyedDataSource
import com.example.moviecatalogue.data.remote.response.Movie
import com.example.moviecatalogue.data.remote.services.MovieServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDataSource constructor(private val movieServices: MovieServices) :
    PageKeyedDataSource<Int, Movie>() {

    private var coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        fetchData(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page + 1)
        }

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(page: Int, callback: (List<Movie>) -> Unit) {
        coroutineScope.launch {
            val getMovieDeferred = movieServices.getMovieAsync(page = page)
            try {
                val movie = getMovieDeferred.await().results
                callback(movie ?: emptyList())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}