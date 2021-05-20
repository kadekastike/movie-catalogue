package com.kadek.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kadek.core.data.Resource
import com.kadek.core.ui.MovieAdapter
import com.kadek.moviecatalogue.DetailActivity
import com.kadek.moviecatalogue.DetailActivity.Companion.EXTRA_MOVIE
import com.kadek.moviecatalogue.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie.*

@Suppress("DEPRECATION", "UNREACHABLE_CODE")
@AndroidEntryPoint
class MovieFragment : Fragment() {

    private var _movieBinding: FragmentMovieBinding? = null
    private val movieBinding get() = _movieBinding!!

    private val movieViewModel : MovieViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _movieBinding = FragmentMovieBinding.inflate(inflater, container, false)
        return movieBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, it)
            intent.putExtra(DetailActivity.EXTRA_TYPE, EXTRA_MOVIE)
            startActivity(intent)
        }

        movieBinding.progressBar.visibility = View.VISIBLE
        movieViewModel.movies.observe(viewLifecycleOwner,  { movies ->
            movieBinding.progressBar.visibility = View.GONE
            if (movies != null) {
                when(movies) {
                    is Resource.Loading<*> -> movieBinding.progressBar.visibility = View.GONE
                    is Resource.Success<*> -> {
                        movieBinding.progressBar.visibility = View.GONE
                        rv_movie.adapter = movieAdapter
                        movieAdapter.setMovies(movies.data)
                    }
                    is Resource.Error<*> -> {
                        movieBinding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Error" + movies.message, Toast.LENGTH_SHORT ).show()
                    }
                }
            }
        })

        movieBinding.rvMovie.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _movieBinding = null
    }
}