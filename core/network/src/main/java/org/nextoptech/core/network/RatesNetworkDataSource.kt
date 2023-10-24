package org.nextoptech.core.network

import kotlinx.coroutines.flow.Flow
import org.nextoptech.core.network.model.RateListNetwork

interface RatesNetworkDataSource {
    fun getRates(): Flow<RateListNetwork>
}