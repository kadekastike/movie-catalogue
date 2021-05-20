package com.kadek.core.data.source.local.room

import androidx.room.*
import com.kadek.core.data.source.local.entity.MovieEntity
import com.kadek.core.data.source.local.entity.TvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(entity = MovieEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie : List<MovieEntity>)

    @Insert(entity = TvEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTv(tv : List<TvEntity>)

    @Query("SELECT * FROM fav_movie")
    fun showMovie() : Flow<List<MovieEntity>>

    @Query("SELECT * FROM fav_tv")
    fun showTv() : Flow<List<TvEntity>>

    @Query("SELECT * FROM fav_movie WHERE favorited = 1")
    fun showFavMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM fav_tv WHERE favorited = 1")
    fun showFavTv() : Flow<List<TvEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movie: MovieEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTv(tv: TvEntity)
}