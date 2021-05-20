package com.kadek.core.domain.usecase

import com.kadek.core.data.Resource
import com.kadek.core.domain.model.Tv
import kotlinx.coroutines.flow.Flow


interface TvUseCase {
    fun getTv(): Flow<Resource<List<Tv>>>
    fun getFavTv(): Flow<List<Tv>>
    fun setFavTv(tv: Tv)
}