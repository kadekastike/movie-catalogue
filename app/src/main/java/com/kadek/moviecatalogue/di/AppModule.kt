package com.kadek.moviecatalogue.di

import com.kadek.core.domain.usecase.MovieInteractor
import com.kadek.core.domain.usecase.MovieUseCase
import com.kadek.core.domain.usecase.TvInteractor
import com.kadek.core.domain.usecase.TvUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase

    @Binds
    abstract fun provideTvUseCase(tvInteractor: TvInteractor): TvUseCase
}