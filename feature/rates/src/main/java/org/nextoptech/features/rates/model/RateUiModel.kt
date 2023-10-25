package org.nextoptech.features.rates.model

import org.nextoptech.core.data.model.RateModel

data class RateUiModel(
    val symbol: String,
    val price: String,
    val direction: MarketDirection,
)

enum class MarketDirection{
    BULL,
    BEAR,
}

fun List<RateModel>.asUiModel(oldList: List<RateModel>) = this.map { rateModel ->
    val oldModel = oldList.find { oldModel ->
        oldModel.symbol == rateModel.symbol
    }

    val direction = if ((oldModel?.price ?: 0.0) >= rateModel.price)
        MarketDirection.BEAR
    else
        MarketDirection.BULL

    RateUiModel(
        symbol = rateModel.symbol,
        price = String.format("%.4f", rateModel.price),
        direction = direction,
    )
}


