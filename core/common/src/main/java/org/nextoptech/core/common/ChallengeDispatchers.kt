package org.nextoptech.core.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: ChallengeDispatchers)

enum class ChallengeDispatchers {
    Default,
    IO,
}
