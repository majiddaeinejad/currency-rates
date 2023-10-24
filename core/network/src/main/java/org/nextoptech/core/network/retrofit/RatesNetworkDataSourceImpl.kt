package org.nextoptech.core.network.retrofit

import kotlinx.coroutines.flow.Flow
import org.nextoptech.core.network.RatesNetworkDataSource
import org.nextoptech.core.network.model.RateListNetwork
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RatesNetworkDataSourceImpl @Inject constructor(
    private val retrofitRatesHelper: RetrofitRatesHelper
) : RatesNetworkDataSource {

    override fun getRates(): Flow<RateListNetwork> {
        return retrofitRatesHelper.getRates()
    }
}
