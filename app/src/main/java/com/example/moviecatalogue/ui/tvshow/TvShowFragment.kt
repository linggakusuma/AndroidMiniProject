package com.example.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.moviecatalogue.R
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

            setHasOptionsMenu(true)
        }.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_item, menu)
        setupSearchView(menu.findItem(R.id.searchFragment).actionView as SearchView?)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item, view?.findNavController() ?: findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    private fun setupSearchView(searchView: SearchView?) {
        searchView?.queryHint = "Search Tv Show"
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean = false

            override fun onQueryTextSubmit(query: String?): Boolean {
                findNavController().navigate(
                    TvShowFragmentDirections.actionNavigationTvShowToSearchTvShowFragment(query.toString())
                )
                return true
            }
        })
    }
}
