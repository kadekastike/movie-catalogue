package com.kadek.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kadek.core.databinding.ItemsListBinding
import com.kadek.core.domain.model.Movie
import com.kadek.core.utils.Helper.POSTER_ENDPOINT
import com.kadek.core.utils.Helper.POSTER_W185
import com.kadek.core.utils.Helper.setImage

@Suppress("DEPRECATION")
class  MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){
    private var listMovies = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setMovies(movies: List<Movie>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsListBinding = ItemsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsListBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class MovieViewHolder(private val binding: ItemsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie : Movie) {
            with(binding) {
                setImage(itemView.context,
                    POSTER_ENDPOINT + POSTER_W185 + movie.poster,
                    imgPoster)
                tvItemTitle.text = movie.name
                tvItemDescription.text = movie.description
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listMovies[adapterPosition])
            }
        }
    }
}