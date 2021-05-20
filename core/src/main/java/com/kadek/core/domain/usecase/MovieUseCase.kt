package com.kadek.core.domain.usecase

import com.kadek.core.data.Resource
import com.kadek.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMovies(): Flow<Resource<List<Movie>>>
    fun getFavMovie(): Flow<List<Movie>>
    fun setFavMovie(movie: Movie)
}