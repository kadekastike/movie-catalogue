package com.kadek.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kadek.core.data.source.local.entity.MovieEntity
import com.kadek.core.data.source.local.entity.TvEntity

@Database(entities = [MovieEntity::class, TvEntity::class],
    version = 1,
    exportSchema = false)

abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

}