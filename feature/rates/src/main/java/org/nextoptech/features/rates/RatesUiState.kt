package org.nextoptech.features.rates

import org.nextoptech.features.rates.model.RateUiModel

sealed interface RatesUiState {
    data object Loading : RatesUiState

    data object LoadFailed : RatesUiState

    data class Success(
        val rates: List<RateUiModel>,
        val lastUpdate: String,
    ) : RatesUiState

}