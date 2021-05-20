package com.kadek.core.di

import android.content.Context
import androidx.room.Room
import com.kadek.core.data.source.local.room.FavoriteDao
import com.kadek.core.data.source.local.room.FavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    private val passphrase : ByteArray = SQLiteDatabase.getBytes("moviecatalogue".toCharArray())
    val factory = SupportFactory(passphrase)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FavoriteDatabase = Room.databaseBuilder(
        context,
        FavoriteDatabase::class.java, "Favorite.db"
    ).fallbackToDestructiveMigration()
        .openHelperFactory(factory)
        .build()

    @Provides
    fun provideFavoriteDao(database: FavoriteDatabase): FavoriteDao = database.favoriteDao()
}