package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.remote.response.DetailResponse
import com.example.moviecatalogue.data.remote.services.MovieServices
import com.example.moviecatalogue.utils.ext.disposedBy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val movieServices: MovieServices) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var _detail = MutableLiveData<DetailResponse>()
    val detail: LiveData<DetailResponse>
        get() = _detail


    fun getDetailMovie(id: Int?) {
        movieServices.getDetailMovie(id = id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _detail.postValue(it)
                }
            ).disposedBy(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}
