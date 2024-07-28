package com.app.travelmate.presentation.ui.activity.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.travelmate.domain.usecase.GetDestinationCatalogUseCase

class HomeViewModelFactory(private val getDestinationCatalogUseCase: GetDestinationCatalogUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(getDestinationCatalogUseCase) as T
    }
}