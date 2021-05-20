package com.kadek.core.domain.usecase

import com.kadek.core.domain.model.Tv
import com.kadek.core.domain.repository.IHomeRepository
import javax.inject.Inject

class TvInteractor @Inject constructor(private val homeRepository: IHomeRepository) : TvUseCase{

    override fun getTv() = homeRepository.getTv()

    override fun getFavTv() = homeRepository.getFavTv()

    override fun setFavTv(tv: Tv) = homeRepository.setFavTv(tv)
}