package org.nextoptech.core.data.model

import org.nextoptech.core.network.model.RateListNetwork

data class RateModel(
    val symbol: String,
    val price: Double,
)

fun RateListNetwork.asDataModel() = rates.map {
    RateModel(
        symbol = it.symbol,
        price = it.price,
    )
}