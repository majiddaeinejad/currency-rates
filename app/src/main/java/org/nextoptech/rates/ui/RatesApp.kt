package org.nextoptech.rates.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import org.nextoptech.rates.navigation.RatesNavHost
import org.nextoptech.features.rates.navigation.ratesRoute

@Composable
fun RatesApp(){
    val navController = rememberNavController()
    RatesNavHost(
        startDestination = ratesRoute,
        navController = navController
    )

}