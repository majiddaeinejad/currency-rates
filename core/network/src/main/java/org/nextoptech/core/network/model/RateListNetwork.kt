package org.nextoptech.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RateListNetwork(
    val rates: List<RateNetwork>
)

@Serializable
data class RateNetwork(
    val symbol: String,
    val price: Double,
)