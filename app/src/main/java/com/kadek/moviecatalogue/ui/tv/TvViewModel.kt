package com.kadek.moviecatalogue.ui.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kadek.core.domain.usecase.TvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(tvUseCase: TvUseCase): ViewModel() {
    val tv = tvUseCase.getTv().asLiveData()
}