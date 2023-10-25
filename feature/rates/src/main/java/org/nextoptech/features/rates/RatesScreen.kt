package org.nextoptech.features.rates

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.nextoptech.feature.rates.R
import org.nextoptech.features.rates.model.MarketDirection
import org.nextoptech.features.rates.model.RateUiModel

@Composable
internal fun RatesRoute(
    modifier: Modifier = Modifier,
    ratesViewModel: RatesViewModel = hiltViewModel(),
) {
    val ratesUiState by ratesViewModel.ratesUiState.collectAsStateWithLifecycle()
    RatesScreen(
        modifier = modifier,
        ratesUiState = ratesUiState,
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
            RatesUiState.LoadFailed,
            -> Unit

            is RatesUiState.Success -> {
                RatesBody(
                    rates = ratesUiState.rates,
                    modifier = modifier,
                    lastUpdate = ratesUiState.lastUpdate,
                )
            }
        }
    }

}

@Composable
private fun RatesBody(
    rates: List<RateUiModel>,
    lastUpdate: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(
            text = "Rates",
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(rates) { rate ->
                RateItem(rate)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier.align(CenterHorizontally),
            text = lastUpdate,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@Composable
private fun RateItem(
    rateModel: RateUiModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.onSurface)
            .padding(start = 16.dp, end = 28.dp, top = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(44.dp),
            painter = painterResource(id = getPairResourceId(rateModel.symbol)),
            contentDescription = rateModel.symbol
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = rateModel.symbol,
            style = MaterialTheme.typography.bodyLarge,
        )

        Text(
            text = String.format("%.4f", rateModel.price),
            style = MaterialTheme.typography.bodyMedium,
            color = getDirectionColor(rateModel.direction),
        )
        Image(
            modifier = modifier.padding(start = 5.dp),
            painter = painterResource(id = getDirectionDrawable(rateModel.direction)),
            contentDescription = stringResource(id = getDirectionContentDescription(rateModel.direction))
        )
    }
}

fun getPairResourceId(name: String): Int {
    return when (name) {
        "EURUSD" -> R.drawable.eurusd
        "GBPJPY" -> R.drawable.gbpjpy
        "AUDCAD" -> R.drawable.audcad
        "JPYAED" -> R.drawable.jpyaed
        "JPYSEK" -> R.drawable.jpysek
        "USDGBP" -> R.drawable.usdgbp
        "USDCAD" -> R.drawable.usdcad
        else -> 0
    }
}

fun getDirectionDrawable(direction: MarketDirection): Int {
    return when(direction){
        MarketDirection.BULL -> R.drawable.rate_up
        MarketDirection.BEAR -> R.drawable.rate_down
    }
}

fun getDirectionContentDescription(direction: MarketDirection): Int{
    return when(direction){
        MarketDirection.BULL -> R.string.market_direction_up
        MarketDirection.BEAR -> R.string.market_direction_up
    }
}

@Composable
fun getDirectionColor(direction: MarketDirection): Color {
    return when(direction){
        MarketDirection.BULL -> MaterialTheme.colorScheme.onTertiary
        MarketDirection.BEAR -> MaterialTheme.colorScheme.error
    }

}



