package org.nextoptech.features.rates.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.nextoptech.features.rates.RatesRoute

const val ratesRoute = "rates_route"
fun NavGraphBuilder.ratesScreen() {
    composable(route = ratesRoute) {
        RatesRoute()
    }
}
