package com.kadek.moviecatalogue.ui.movie

import androidx.lifecycle.asLiveData
import androidx.lifecycle.ViewModel
import com.kadek.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val movies = movieUseCase.getMovies().asLiveData()
}