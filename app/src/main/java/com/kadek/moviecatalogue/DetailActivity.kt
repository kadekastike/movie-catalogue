package com.kadek.moviecatalogue

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kadek.core.domain.model.Movie
import com.kadek.core.domain.model.Tv
import com.kadek.core.utils.Helper.POSTER_ENDPOINT
import com.kadek.core.utils.Helper.POSTER_W780
import com.kadek.core.utils.Helper.setImage
import com.kadek.moviecatalogue.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV = "extra_tv"
    }
    private lateinit var binding : ActivityDetailBinding
    private val viewModel : DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataType = intent.getStringExtra(EXTRA_TYPE)

        if (dataType == EXTRA_MOVIE) {
            val dataMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
            loadDataDetail(dataMovie, null)
        } else if (dataType == EXTRA_TV) {
            val dataTv = intent.getParcelableExtra<Tv>(EXTRA_DATA)
            loadDataDetail(null, dataTv)
        }
    }

    private fun loadDataDetail(movieEntity: Movie?, tvEntity: Tv?) {
        supportActionBar?.title = movieEntity?.name ?: tvEntity?.title
        binding.title.text = movieEntity?.name ?: tvEntity?.title
        binding.year.text = movieEntity?.releaseDate ?: tvEntity?.firstAirDate
        binding.description.text = movieEntity?.description ?: tvEntity?.description

        val poster = movieEntity?.poster ?: tvEntity?.poster
        val favoriteStatus = movieEntity?.favorited ?: tvEntity?.favorited

        setImage(this, POSTER_ENDPOINT + POSTER_W780 + poster, binding.poster)

        favoriteStatus?.let {
            setFavState(it)
        }
        binding.favorite.setOnClickListener {
            addToFavorite(movieEntity, tvEntity)
            setFavState(!favoriteStatus!!)
        }
    }

    private fun setFavState(state: Boolean) {
        val icon = binding.favorite
        if (state) {
            icon.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            icon.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun addToFavorite(movieEntity: Movie?, tvEntity: Tv?) {
        if (movieEntity != null) {
            if (movieEntity.favorited) {
                Toast.makeText(this,"Removed From Favorite", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Added To Favorite", Toast.LENGTH_SHORT).show()
            }
            viewModel.setFavMovie(movieEntity)

        } else {
            if (tvEntity != null) {
                if (tvEntity.favorited) {
                    Toast.makeText(this,"Removed From Favorite", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Added To Favorite", Toast.LENGTH_SHORT).show()
                }
                viewModel.setFavTv(tvEntity)
            }
        }
    }
}