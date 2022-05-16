package com.arifwidayana.challangechapter6.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arifwidayana.challangechapter6.databinding.ItemCardMoviesBinding
import com.arifwidayana.challangechapter6.model.pojo.listmovies.ResultListPlaying
import com.arifwidayana.challangechapter6.model.utils.Constant
import com.bumptech.glide.Glide

class ListPlayingMoviesAdapter(private val onClick: (ResultListPlaying) -> Unit) : ListAdapter<ResultListPlaying, ListPlayingMoviesAdapter.MoviesHolder>(
    Differ()
) {

    class MoviesHolder(
        private val binding: ItemCardMoviesBinding,
        private val onClick: (ResultListPlaying) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun result(currentMovies: ResultListPlaying) {
            binding.apply {
                tvTitle.text = currentMovies.title
                tvOverview.text = currentMovies.overview
                tvRate.text = currentMovies.voteAverage.toString()
                Glide.with(root)
                    .load("${Constant.BASE_IMAGE}${currentMovies.posterPath}")
                    .into(ivPoster)
                root.setOnClickListener {
                    onClick(currentMovies)
                }
            }
        }
    }

    class Differ : DiffUtil.ItemCallback<ResultListPlaying>() {
        override fun areItemsTheSame(oldItem: ResultListPlaying, newItem: ResultListPlaying): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ResultListPlaying, newItem: ResultListPlaying): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val binding = ItemCardMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.result(getItem(position))
    }
}