package com.example.moviecatalogue.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogue.data.remote.response.Movie
import com.example.moviecatalogue.ui.movie.MovieAdapter
import com.example.moviecatalogue.ui.notification.MovieNotificationAdapter
import com.example.moviecatalogue.ui.search.SearchAdapter
import com.example.moviecatalogue.ui.searchtv.SearchTvShowAdapter
import com.example.moviecatalogue.ui.tvshow.TvShowAdapter

@BindingAdapter("listMovie")
fun recyclerViewMovie(recyclerView: RecyclerView, data: PagedList<Movie>?) {
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
    adapter.notifyDataSetChanged()
}

@BindingAdapter("listTvShow")
fun recyclerViewTvShow(recyclerView: RecyclerView, data: PagedList<Movie>?) {
    val adapter = recyclerView.adapter as TvShowAdapter
    adapter.submitList(data)
    adapter.notifyDataSetChanged()
}

@BindingAdapter("listSearch")
fun recyclerViewMovieSearch(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as SearchAdapter
    adapter.submitList(data)
    adapter.notifyDataSetChanged()
}

@BindingAdapter("listSearchTvShow")
fun recyclerViewTvShowSearch(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as SearchTvShowAdapter
    adapter.submitList(data)
    adapter.notifyDataSetChanged()
}

@BindingAdapter("listMovieNotification")
fun recylerViewMovieNotification(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieNotificationAdapter
    adapter.submitList(data)
    adapter.notifyDataSetChanged()
}

@BindingAdapter("imageMovie")
fun bindImageMovie(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load("https://image.tmdb.org/t/p/w500/$imgUrl")
            .into(imgView)
    }
}

@BindingAdapter("image")
fun bindImage(imageView: ImageView, imgUrl: String?) {
    imgUrl.let {
        Glide.with(imageView.context)
            .load(it)
            .into(imageView)
    }
}