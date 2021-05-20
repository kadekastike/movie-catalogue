package com.kadek.core.utils

import com.kadek.core.data.source.local.entity.MovieEntity
import com.kadek.core.data.source.local.entity.TvEntity
import com.kadek.core.data.source.remote.response.MovieResponse
import com.kadek.core.data.source.remote.response.TvResponse
import com.kadek.core.domain.model.Movie
import com.kadek.core.domain.model.Tv

object DataMapper {
    fun movieMapResponseToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val listMovie = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                name = it.title,
                releaseDate = it.releaseDate,
                description = it.overview,
                poster = it.posterPath,
                favorited = false
            )
            listMovie.add(movie)
        }
        return listMovie
    }

    fun tvMapResponseToEntities(input: List<TvResponse>): List<TvEntity> {
        val listTv = ArrayList<TvEntity>()
        input.map {
            val tv = TvEntity(
                tvId = it.id,
                name = it.name,
                releaseDate = it.firstAirDate,
                description = it.overview,
                poster = it.posterPath,
                favorited = false
            )
            listTv.add(tv)
        }
        return listTv
    }

    fun movieMapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.movieId,
                name = it.name!!,
                releaseDate = it.releaseDate!!,
                description = it.description!!,
                poster = it.poster!!,
                favorited = it.favorited
            )
        }

    fun tvMapEntitiesToDomain(input: List<TvEntity>): List<Tv> =
        input.map {
            Tv(
                id = it.tvId,
                title = it.name!!,
                firstAirDate = it.releaseDate!!,
                description = it.description!!,
                poster = it.poster!!,
                favorited = it.favorited
            )
        }

    fun movieDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.id,
        name = input.name,
        releaseDate = input.releaseDate,
        description = input.description,
        poster = input.poster,
        favorited = input.favorited
    )

    fun tvDomainToEntity(input: Tv) = TvEntity(
        tvId = input.id,
        name = input.title,
        releaseDate = input.firstAirDate,
        description = input.description,
        poster = input.poster,
        favorited = input.favorited
    )
}