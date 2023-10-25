package org.nextoptech.core.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: RatesDispatchers)

enum class RatesDispatchers {
    Default,
    IO,
}
