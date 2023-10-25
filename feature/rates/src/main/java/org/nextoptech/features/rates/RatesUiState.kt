package org.nextoptech.features.rates

import org.nextoptech.core.data.model.RateModel

sealed interface RatesUiState {
    data object Loading : RatesUiState

    data object LoadFailed : RatesUiState

    data class Success(
        val rates: List<RateModel>,
        val lastUpdate: String,
    ) : RatesUiState

}