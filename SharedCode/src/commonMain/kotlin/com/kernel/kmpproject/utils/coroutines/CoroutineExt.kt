package com.kernel.kmpproject.utils.coroutines

import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 * Equivalent to [launch] but return [Unit] instead of [Job].
 *
 * Mainly for usage when you want to lift [launch] to return. Example:
 *
 * ```
 * override fun loadData() = launchSilent {
 *     // code
 * }
 * ```
 */
fun launchSilent(
  context: CoroutineContext = Dispatchers.Main,
  exceptionHandler: CoroutineExceptionHandler? = null,
  job: Job,
  start: CoroutineStart = CoroutineStart.DEFAULT,
  block: suspend CoroutineScope.() -> Unit
) {
  val coroutineScope = if (exceptionHandler != null) {
    CoroutineScope(context + job + exceptionHandler)
  } else {
    CoroutineScope(context + job)
  }
  coroutineScope.launch(context, start, block)
}

fun timer(
  delay: Duration = Duration.ZERO,
  repeatEvery: Duration
) = flow {
  kotlinx.coroutines.delay(delay.toLongMilliseconds())
  while (true) {
    emit(Unit)
    kotlinx.coroutines.delay(repeatEvery.toLongMilliseconds())
  }
}