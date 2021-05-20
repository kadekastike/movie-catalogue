package com.kadek.core.domain.usecase

import com.kadek.core.data.Resource
import com.kadek.core.domain.model.Movie
import com.kadek.core.domain.repository.IHomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val homeRepository: IHomeRepository): MovieUseCase {

    override fun getMovies() : Flow<Resource<List<Movie>>> = homeRepository.getMovies()

    override fun getFavMovie() = homeRepository.getFavMovie()

    override fun setFavMovie(movie: Movie) = homeRepository.setFavMovie(movie)
}