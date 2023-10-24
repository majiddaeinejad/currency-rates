package org.nextoptech.core.network.retrofit

import org.nextoptech.core.network.model.RateListNetwork
import retrofit2.http.GET

interface RetrofitRatesApi {
    @GET("code-challenge/index.php")
    suspend fun getRates(): RateListNetwork

}