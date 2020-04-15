package com.example.moviecatalogue.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.remote.response.Movie
import com.example.moviecatalogue.data.remote.services.MovieServices
import com.example.moviecatalogue.utils.ext.disposedBy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val movieServices: MovieServices) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var _search = MutableLiveData<List<Movie>>()
    val search: LiveData<List<Movie>>
        get() = _search

    fun getSearch(query: String) {
        movieServices.getSearch(query = query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _search.value = it.results
                }
            ).disposedBy(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
