package com.example.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.moviecatalogue.databinding.DetailFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<DetailViewModel> { viewModelFactory }

    private val movieArgs: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.id = movieArgs.id
        return DetailFragmentBinding.inflate(inflater).apply {
            viewModel = this@DetailFragment.viewModel
            lifecycleOwner = this@DetailFragment
        }.root
    }
}
