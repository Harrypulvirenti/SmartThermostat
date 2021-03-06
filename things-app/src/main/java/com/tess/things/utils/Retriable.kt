package com.tess.things.utils

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.right
import com.tess.core.models.GenericError
import com.tess.things.models.RetryError
import kotlin.random.Random
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private const val RETRY_DELAY = 1500L
private const val RETRY_COUNT = 3

class Retriable<E : GenericError, T : Any>(
    private val delayMillis: Long = RETRY_DELAY,
    private var retryCount: Int = RETRY_COUNT,
    private val block: suspend () -> Either<E, T>
) {

    suspend fun retryOn(retryCondition: (T) -> Boolean): Either<GenericError, T> =
        withContext(Dispatchers.Default) {
            block.invoke()
                .flatMap {
                    val canRetry = retryCondition(it)
                    when {
                        canRetry && retryCount > 0 -> {
                            retry(retryCondition)
                        }
                        !canRetry -> {
                            it.right()
                        }
                        else -> {
                            RetryError("Retry").left()
                        }
                    }
                }
        }

    private suspend fun retry(retryCondition: (T) -> Boolean): Either<GenericError, T> {
        delay(Random.nextLong(0, delayMillis))
        retryCount--
        return retryOn(retryCondition)
    }
}
