package org.nextoptech.features.rates.model

import androidx.annotation.DrawableRes
import org.nextoptech.core.data.model.RateModel
import org.nextoptech.feature.rates.R

data class RateUiModel(
    val symbol: String,
    val price: Double,
    @DrawableRes val image: Int,
)

fun List<RateModel>.asUiModel() = this.map {
    RateUiModel(
        symbol = it.symbol,
        price = it.price,
        image = getResourceId(it.symbol),
    )
}

fun getResourceId(name: String): Int {
    return when(name){
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

