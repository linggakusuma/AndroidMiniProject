package com.example.moviecatalogue.ui.notification

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ActivityMovieNotificationBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MovieNotificationActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MovieNotificationViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMovieNotificationBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_movie_notification)

        binding.apply {
            viewModel = this@MovieNotificationActivity.viewModel
            recyclerViewMovieNotification.adapter = MovieNotificationAdapter()
            lifecycleOwner = this@MovieNotificationActivity
        }
    }
}
