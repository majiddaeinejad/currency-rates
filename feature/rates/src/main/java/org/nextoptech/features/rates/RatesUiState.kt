package org.nextoptech.features.rates

import org.nextoptech.features.rates.model.RateUiModel

sealed interface RatesUiState {
    data object Loading : RatesUiState

    data class LoadFailed(val message: String) : RatesUiState

    data class Success(
        val rates: List<RateUiModel>,
        val lastUpdate: String,
    ) : RatesUiState

}