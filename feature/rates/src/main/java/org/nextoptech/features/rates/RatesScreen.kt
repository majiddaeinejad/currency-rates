package org.nextoptech.features.rates

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.nextoptech.core.data.model.RateModel

@Composable
internal fun RatesRoute(
    modifier: Modifier = Modifier,
    ratesViewModel: RatesViewModel = hiltViewModel(),
) {
    val ratesUiState by ratesViewModel.ratesUiState.collectAsStateWithLifecycle()
    RatesScreen(
        modifier = modifier,
        ratesUiState =  ratesUiState,
    )
}

@Composable
internal fun RatesScreen(
    modifier: Modifier = Modifier,
    ratesUiState: RatesUiState,
) {

    Column(modifier = modifier) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        when (ratesUiState) {
            RatesUiState.Loading,
            RatesUiState.LoadFailed, -> Unit
            is RatesUiState.Success -> {
                RatesBody(
                    rates = ratesUiState.rates,
                    modifier = modifier,
                )
            }
        }
    }

}

@Composable
private fun RatesBody(rates: List<RateModel>, modifier: Modifier) {
    LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
        items(rates) { rateModel ->
            RateItem(rateModel)
        }
    }
}

@Composable
private fun RateItem(rateModel: RateModel){
    Text(
        text = rateModel.symbol,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
    )

}

