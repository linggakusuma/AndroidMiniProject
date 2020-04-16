package com.example.moviecatalogue.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel() {
    private val viewModeJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModeJob.cancel()
    }
}