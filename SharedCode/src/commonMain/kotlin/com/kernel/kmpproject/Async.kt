package com.kernel.kmpproject

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

expect val ApplicationDispatcher: CoroutineContext

expect fun CoroutineScope.launchInMain(block: suspend CoroutineScope.() -> Unit): Job

expect fun <T> Flow<T>.flowOnBackground(): Flow<T>

expect fun <T> runBlocking(
  context: CoroutineContext = EmptyCoroutineContext,
  block: suspend CoroutineScope.() -> T
): T