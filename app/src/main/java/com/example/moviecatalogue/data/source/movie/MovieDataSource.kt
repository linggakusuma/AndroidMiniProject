package com.example.moviecatalogue.data.source.movie

import androidx.paging.PageKeyedDataSource
import com.example.moviecatalogue.data.remote.response.Movie
import com.example.moviecatalogue.data.remote.services.MovieServices
import com.example.moviecatalogue.utils.ext.disposedBy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MovieDataSource constructor(private val movieServices: MovieServices) :
    PageKeyedDataSource<Int, Movie>() {

    private val compositeDisposable = CompositeDisposable()

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
        movieServices.getMovie(page = page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    val movie = it.results
                    callback(movie ?: emptyList())
                }
            ).disposedBy(compositeDisposable)
    }
}