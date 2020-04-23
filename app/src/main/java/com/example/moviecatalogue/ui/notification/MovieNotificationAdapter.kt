package com.example.moviecatalogue.ui.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.data.remote.response.Movie
import com.example.moviecatalogue.databinding.ListMovieBinding

class MovieNotificationAdapter :
    ListAdapter<Movie, MovieNotificationAdapter.MovieNotificationViewHolder>(Diffcallback) {

    class MovieNotificationViewHolder(private val binding: ListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            binding.apply {
                movie = item
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


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieNotificationViewHolder {
        return MovieNotificationViewHolder(ListMovieBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(
        holder: MovieNotificationViewHolder,
        position: Int
    ) {
        val movie = getItem(position)
        holder.apply {
            bind(movie)
        }
    }
}