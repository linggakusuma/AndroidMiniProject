package com.example.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TvShowFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TvShowViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentTvShowBinding.inflate(inflater).apply {
            viewModel = this@TvShowFragment.viewModel
            recyclerViewTvShow.adapter =
                TvShowAdapter()
            lifecycleOwner = this@TvShowFragment
        }.root
    }
}
