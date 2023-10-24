package org.nextoptech.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.nextoptech.core.data.repository.RatesRepository
import org.nextoptech.core.data.repository.RatesRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsRatesRepository(
        ratesRepositoryImpl: RatesRepositoryImpl,
    ): RatesRepository

}
