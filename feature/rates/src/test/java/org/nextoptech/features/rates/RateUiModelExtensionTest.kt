package org.nextoptech.features.rates

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.nextoptech.core.data.model.RateModel
import org.nextoptech.features.rates.model.MarketDirection
import org.nextoptech.features.rates.model.asUiModel

class RateModelExtensionTest {

    @Test
    fun `test asUiModel extension function`() {
        //Given
        val oldList = listOf(
            RateModel("USDCAD", 1.0),
            RateModel("EURUSD", 2.0)
        )
        val newList = listOf(
            RateModel("USDCAD", 1.1),
            RateModel("EURUSD", 1.9)
        )

        //WHEN
        val uiModels = newList.asUiModel(oldList)

        //THEN
        assertEquals(2, uiModels.size)

        assertEquals("USDCAD", uiModels[0].symbol)
        assertEquals("1.1000", uiModels[0].price)
        assertEquals(MarketDirection.BULL, uiModels[0].direction)

        assertEquals("EURUSD", uiModels[1].symbol)
        assertEquals("1.9000", uiModels[1].price)
        assertEquals(MarketDirection.BEAR, uiModels[1].direction)

    }
}