package org.nextoptech.core.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.nextoptech.core.network.RatesNetworkDataSource
import org.nextoptech.core.network.retrofit.RatesNetworkDataSourceImpl
import org.nextoptech.core.network.retrofit.RetrofitRatesHelper
import org.nextoptech.core.network.retrofit.RetrofitRatesNetwork

@Module
@InstallIn(SingletonComponent::class)
interface RetrofitNetworkModule {
    @Binds
    fun bindRetrofitRatesNetwork(ratesNetworkDataSourceImpl: RatesNetworkDataSourceImpl): RatesNetworkDataSource

    @Binds
    fun bindRetrofitRatesHelper(retrofitRatesNetwork: RetrofitRatesNetwork): RetrofitRatesHelper


}