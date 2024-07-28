package com.app.travelmate.domain.repository

import com.app.travelmate.data.model.destination.DestinationCatalog

interface DestinationRepository {

    suspend fun getDestinationList(): DestinationCatalog?
}