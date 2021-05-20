package com.kadek.core.di

import com.kadek.core.data.source.repository.HomeRepository
import com.kadek.core.domain.repository.IHomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideHomeRepository(homeRepository: HomeRepository): IHomeRepository

}