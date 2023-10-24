package org.nextoptech.core.data.repository

import kotlinx.coroutines.flow.Flow
import org.nextoptech.core.data.model.RateModel

interface RatesRepository {

    fun getRates(): Flow<List<RateModel>>
}