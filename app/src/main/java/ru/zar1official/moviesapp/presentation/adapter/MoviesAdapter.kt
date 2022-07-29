package ru.zar1official.moviesapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import dagger.hilt.android.scopes.ActivityScoped
import ru.zar1official.moviesapp.R
import ru.zar1official.moviesapp.data.models.MovieEntity
import ru.zar1official.moviesapp.databinding.ListItemBinding
import javax.inject.Inject

@ActivityScoped
class MoviesAdapter @Inject constructor() :
    PagingDataAdapter<MovieEntity, MoviesAdapter.MovieHolder>(movieDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.binding.apply {
                movieTitle.text = currentItem.title
                movieDescription.text = currentItem.description
                movieImage.load(currentItem.image.link) {
                    crossfade(true)
                    placeholder(R.drawable.ic_launcher_foreground)
                    transformations(CircleCropTransformation())
                }
            }
        }
    }

    class MovieHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val movieDiffUtil = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}