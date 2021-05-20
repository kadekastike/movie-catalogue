package com.kadek.favorites.ui.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kadek.core.ui.MovieAdapter
import com.kadek.favorites.DaggerFavoriteComponent
import com.kadek.favorites.ViewModelFactory
import com.kadek.favorites.ui.FavoriteViewModel
import com.kadek.moviecatalogue.DetailActivity
import com.kadek.moviecatalogue.R
import com.kadek.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.kadek.moviecatalogue.di.FavoriteModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteMovieFragment : Fragment() {
    @Inject
    lateinit var factory: ViewModelFactory

    private var _favoriteMovieBinding: FragmentFavoriteMovieBinding? = null
    private val favoriteMovieBinding get() = _favoriteMovieBinding!!

    private val favoriteViewModel: FavoriteViewModel by viewModels{
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _favoriteMovieBinding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return favoriteMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, it)
                intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.EXTRA_MOVIE)
                startActivity(intent)
            }

            favoriteMovieBinding.progressBar.visibility = View.VISIBLE
            favoriteViewModel.favMovies.observe(viewLifecycleOwner, {
                movieAdapter.setMovies(it)
                favoriteMovieBinding.progressBar.visibility = View.GONE
                if (it != null) {
                    if (it.isNullOrEmpty()) {
                        favoriteMovieBinding.progressBar.visibility = View.GONE
                        Toast.makeText(activity, R.string.no_fav_movie, Toast.LENGTH_SHORT).show()
                    }
                }
            })

            with(favoriteMovieBinding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder()
            .context(context)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    context,
                    FavoriteModuleDependencies::class.java)
            )
            .build()
            .inject2(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _favoriteMovieBinding = null
    }
}
