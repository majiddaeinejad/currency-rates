package org.nextoptech.features.rates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.nextoptech.core.data.model.RateModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

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
                    lastUpdate = ratesUiState.lastUpdate,
                )
            }
        }
    }

}

@Composable
private fun RatesBody(
    rates: List<RateModel>,
    lastUpdate: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
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
            style = MaterialTheme.typography.labelSmall, // Adjust the style as per your requirements
            color = Color.Gray
        )
    }
}

@Composable
private fun RateItem(
    rateModel: RateModel,
    modifier: Modifier = Modifier
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.onSurface)
            .padding(start = 16.dp, end = 28.dp, top = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = rateModel.symbol,
            style = MaterialTheme.typography.bodyLarge,
        )

        Text(
            text = String.format("%.4f", rateModel.price),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

