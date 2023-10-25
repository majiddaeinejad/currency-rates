package org.nextoptech.features.rates

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.nextoptech.core.data.model.RateModel
import org.nextoptech.core.data.repository.RatesRepository

@ExperimentalCoroutinesApi
class RatesViewModelTest {

    lateinit var fakeRepository: FakeRatesRepository


    @Before
    fun setup() {
        fakeRepository = FakeRatesRepository()
    }


    @Test
    fun `test loading state`() = runTest {
        //GIVEN
        val viewModel = RatesViewModel(fakeRepository)
        fakeRepository.emit(emptyList())

        //WHEN
        backgroundScope.launch {
            viewModel.ratesUiState.collect()
        }

        //THEN
        assertEquals(viewModel.ratesUiState.value, RatesUiState.Loading)

    }


    class FakeRatesRepository : RatesRepository {
        private val flow = MutableSharedFlow<List<RateModel>>()
        suspend fun emit(value: List<RateModel>) = flow.emit(value)

        override fun getRates(): Flow<List<RateModel>>  = flow
    }

}
