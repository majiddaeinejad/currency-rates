package org.nextoptech.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.nextoptech.core.data.model.RateModel
import org.nextoptech.core.data.model.asDataModel
import org.nextoptech.core.network.RatesNetworkDataSource
import javax.inject.Inject

class RatesRepositoryImpl @Inject constructor(
    private val ratesNetworkDataSource: RatesNetworkDataSource
): RatesRepository {
    override fun getRates(): Flow<List<RateModel>> {
        return ratesNetworkDataSource.getRates().map {
            it.asDataModel()
        }
    }

}