package com.kadek.core.domain.repository

import com.kadek.core.data.Resource
import com.kadek.core.domain.model.Movie
import com.kadek.core.domain.model.Tv
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {
    fun getMovies(): Flow<Resource<List<Movie>>>

    fun getTv(): Flow<Resource<List<Tv>>>

    fun getFavMovie(): Flow<List<Movie>>

    fun getFavTv(): Flow<List<Tv>>

    fun setFavMovie(movie: Movie)

    fun setFavTv(tv: Tv)
}