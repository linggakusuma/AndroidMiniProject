package com.example.moviecatalogue.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.remote.response.DetailResponse
import com.example.moviecatalogue.data.remote.services.MovieServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val movieServices: MovieServices) : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _detail = MutableLiveData<DetailResponse>()
    val detail: LiveData<DetailResponse>
        get() = _detail

    var id: Int? = null

    init {
        getDetailMovie()
    }

    private fun getDetailMovie() {
        coroutineScope.launch {
            val getMovieDeferred = movieServices.getDetailMovieAsync(id = id)
            try {
                val detail = getMovieDeferred.await()
                _detail.value = detail
                Log.i("cekgenre", detail.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
