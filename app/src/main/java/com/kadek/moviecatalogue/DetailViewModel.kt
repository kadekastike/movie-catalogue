package com.kadek.moviecatalogue

import androidx.lifecycle.ViewModel
import com.kadek.core.domain.model.Movie
import com.kadek.core.domain.model.Tv
import com.kadek.core.domain.usecase.MovieUseCase
import com.kadek.core.domain.usecase.TvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase, private val tvUseCase: TvUseCase) : ViewModel() {

    fun setFavMovie(movie : Movie) {
        movieUseCase.setFavMovie(movie)
    }

    fun setFavTv(tv : Tv) {
        tvUseCase.setFavTv(tv)
    }
}