package org.nextoptech.features.rates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.nextoptech.core.common.result.Result
import org.nextoptech.core.common.result.asResult
import org.nextoptech.core.data.model.RateModel
import org.nextoptech.core.data.repository.RatesRepository
import org.nextoptech.features.rates.model.asUiModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class RatesViewModel @Inject constructor(
    ratesRepository: RatesRepository,
) : ViewModel() {

    private val reloadTrigger = MutableStateFlow(0)

    private var previousRates = emptyList<RateModel>()

    val ratesUiState: StateFlow<RatesUiState> = reloadTrigger
        .flatMapLatest {
            ratesRepository.getRates()
                .asResult()
                .map { result ->
                    when(result){
                        is Result.Error -> RatesUiState.LoadFailed(
                            result.exception?.message ?: "An Error Occurred"
                        )
                        Result.Loading -> RatesUiState.Loading
                        is Result.Success -> {
                            RatesUiState.Success(
                                result.data.asUiModel(previousRates), getCurrentTimestamp()
                            ).also {
                                previousRates = result.data
                            }

                        }
                    }
                }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RatesUiState.Loading,
        )

    private fun getCurrentTimestamp(): String {
        val current = Date()
        val formatter = SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault())
        return "Last updated: " + formatter.format(current)
    }

    fun tryAgain() {
        reloadTrigger.value++
    }

}
