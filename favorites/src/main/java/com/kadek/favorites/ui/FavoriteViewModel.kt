package com.kadek.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kadek.core.domain.usecase.MovieUseCase
import com.kadek.core.domain.usecase.TvUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(movieUseCase: MovieUseCase, tvUseCase: TvUseCase): ViewModel(){
    val favMovies = movieUseCase.getFavMovie().asLiveData()
    val favTv = tvUseCase.getFavTv().asLiveData()
}