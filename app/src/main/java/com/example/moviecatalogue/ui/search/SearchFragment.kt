package com.example.moviecatalogue.ui.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.SearchFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SearchFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SearchViewModel> { viewModelFactory }

    private val movieArgs: SearchFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.query = movieArgs.query
        return SearchFragmentBinding.inflate(inflater).apply {
            viewModel = this@SearchFragment.viewModel
            recyclerViewMovieSearch.adapter = SearchAdapter()
            lifecycleOwner = this@SearchFragment

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
        searchView?.queryHint = "Search Movie"
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean = false

            override fun onQueryTextSubmit(query: String?): Boolean {
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentSelf(query.toString())
                )
                return true
            }

        })
    }
}
