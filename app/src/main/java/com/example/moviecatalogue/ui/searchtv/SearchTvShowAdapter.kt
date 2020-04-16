package com.example.moviecatalogue.ui.searchtv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.data.remote.response.Movie
import com.example.moviecatalogue.databinding.ListMovieBinding

class SearchTvShowAdapter : ListAdapter<Movie, SearchTvShowAdapter.MovieViewHolder>(Diffcallback) {

    class MovieViewHolder(private val binding: ListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie, listener: View.OnClickListener) {
            binding.apply {
                movie = item
                clickListener = listener
                executePendingBindings()
            }
        }
    }

    companion object Diffcallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ListMovieBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.apply {
            bind(movie, createOnClickListener(movie))
        }
    }

    private fun createOnClickListener(movie: Movie): View.OnClickListener {
        return View.OnClickListener {
            val direction =
                SearchTvShowFragmentDirections.actionSearchTvShowFragmentToDetailTvShowFragment(
                    movie.id?.toInt() ?: 0,
                    movie.name.toString()
                )
            it.findNavController().navigate(direction)
        }
    }
}