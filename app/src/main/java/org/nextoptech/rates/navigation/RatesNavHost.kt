package org.nextoptech.rates.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.nextoptech.features.rates.navigation.ratesScreen

@Composable
fun RatesNavHost(
    modifier: Modifier = Modifier,
    startDestination: String,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        ratesScreen()
    }
}
