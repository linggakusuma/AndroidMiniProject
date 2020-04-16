package com.example.moviecatalogue.ui.detailtvshow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.remote.response.DetailResponse
import com.example.moviecatalogue.data.remote.services.MovieServices
import com.example.moviecatalogue.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailTvShowViewModel @Inject constructor(private val movieServices: MovieServices) :
    BaseViewModel() {

    private var coroutineScope = CoroutineScope(Dispatchers.Main)

    private var _detail = MutableLiveData<DetailResponse>()
    val detail: LiveData<DetailResponse>
        get() = _detail

    var id: Int? = null

    init {
        getDetailTvShow()
    }

    private fun getDetailTvShow() {
        coroutineScope.launch {
            val getMovieDeferred = movieServices.getDetailTvAsync(id = id)
            try {
                val detail = getMovieDeferred.await()
                _detail.value = detail
                Log.i("cekgenre", detail.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
