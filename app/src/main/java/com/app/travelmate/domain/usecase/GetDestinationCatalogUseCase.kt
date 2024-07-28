package com.app.travelmate.domain.usecase

import com.app.travelmate.R
import com.app.travelmate.data.model.destination.DestinationCatalog
import com.app.travelmate.domain.common.ResponseState
import com.app.travelmate.domain.repository.DestinationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDestinationCatalogUseCase(private val destinationRepository: DestinationRepository) {
    suspend operator fun invoke(): Flow<ResponseState<DestinationCatalog>> = flow {
        emit(ResponseState.Loading)
        val destinationData = destinationRepository.getDestinationList()
        if (destinationData != null) {
            emit(ResponseState.SuccessState(destinationData))
        } else {
            emit(ResponseState.ErrorState(message = R.string.no_data_message))
        }
    }
}