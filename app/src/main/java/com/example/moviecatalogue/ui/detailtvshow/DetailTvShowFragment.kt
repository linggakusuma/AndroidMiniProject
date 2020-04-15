package com.example.moviecatalogue.ui.detailtvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.moviecatalogue.databinding.DetailTvShowFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DetailTvShowFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<DetailTvShowViewModel> { viewModelFactory }

    private val movieArgs: DetailTvShowFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.id = movieArgs.id
        return DetailTvShowFragmentBinding.inflate(inflater).apply {
            viewModel = this@DetailTvShowFragment.viewModel
            lifecycleOwner = this@DetailTvShowFragment
        }.root
    }
}