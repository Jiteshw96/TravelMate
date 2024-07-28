package com.app.travelmate.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.travelmate.domain.usecase.GetDestinationCatalogUseCase
import com.app.travelmate.presentation.activity.home.HomeViewModel

class HomeViewModelFactory(private val getDestinationCatalogUseCase: GetDestinationCatalogUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(getDestinationCatalogUseCase) as T
    }
}