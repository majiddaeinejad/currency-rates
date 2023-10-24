package org.nextoptech.core.network.retrofit

import kotlinx.coroutines.flow.Flow
import org.nextoptech.core.network.model.RateListNetwork

interface RetrofitRatesHelper {
    fun getRates(): Flow<RateListNetwork>
}