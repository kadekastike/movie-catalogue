package com.kadek.moviecatalogue.di

import com.kadek.core.domain.usecase.MovieUseCase
import com.kadek.core.domain.usecase.TvUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun movieUseCase(): MovieUseCase
    fun tvUseCase(): TvUseCase
}