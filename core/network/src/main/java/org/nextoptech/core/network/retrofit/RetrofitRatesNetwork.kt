package org.nextoptech.core.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import org.nextoptech.core.common.ChallengeDispatchers.IO
import org.nextoptech.core.common.Dispatcher
import org.nextoptech.core.network.BuildConfig
import retrofit2.Retrofit
import javax.inject.Inject

private const val RATES_BASE_URL = BuildConfig.RATES_BASE_URL
private const val DURATION_MILLIS = 120000L

class RetrofitRatesNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : RetrofitRatesHelper {
    private val networkApi = Retrofit.Builder()
        .baseUrl(RATES_BASE_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitRatesApi::class.java)

    override fun getRates() = flow {
        while(true) {
            emit(networkApi.getRates())
            delay(DURATION_MILLIS)
        }
    }.flowOn(ioDispatcher)
}