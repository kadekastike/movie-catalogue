package com.kadek.core.data.source.local

import com.kadek.core.data.source.local.entity.MovieEntity
import com.kadek.core.data.source.local.entity.TvEntity
import com.kadek.core.data.source.local.room.FavoriteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val mFavoriteDao: FavoriteDao) {

    suspend fun insertMovie(movie : List<MovieEntity>) = mFavoriteDao.insertMovie(movie)

    suspend fun insertTv(tv : List<TvEntity>) = mFavoriteDao.insertTv(tv)

    fun showMovie(): Flow<List<MovieEntity>> = mFavoriteDao.showMovie()

    fun showTv(): Flow<List<TvEntity>> = mFavoriteDao.showTv()

    fun showFavMovie() : Flow<List<MovieEntity>> = mFavoriteDao.showFavMovie()

    fun showFavTv() : Flow<List<TvEntity>> = mFavoriteDao.showFavTv()

    fun setFavMovie(movie: MovieEntity) {
        movie.favorited = !movie.favorited
        mFavoriteDao.updateMovie(movie)
    }

    fun setFavTv(tv: TvEntity) {
        tv.favorited = !tv.favorited
        mFavoriteDao.updateTv(tv)
    }
}