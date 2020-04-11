package com.kernel.kmpproject.di

import com.kernel.kmpproject.ApplicationDispatcher
import com.kernel.kmpproject.data.repository.AppIndexesRepository
import com.kernel.kmpproject.data.repository.AppQuoteRepository
import com.kernel.kmpproject.data.repository.IndexesRepository
import com.kernel.kmpproject.data.repository.QuoteRepository
import com.kernel.kmpproject.data.source.network.AppNetworkSource
import com.kernel.kmpproject.data.source.network.FmpApi
import com.kernel.kmpproject.data.source.network.NetworkSource
import com.kernel.kmpproject.domain.usecase.AppGetIndexesUseCase
import com.kernel.kmpproject.domain.usecase.AppGetQuoteUseCase
import com.kernel.kmpproject.domain.usecase.GetIndexesUseCase
import com.kernel.kmpproject.domain.usecase.GetQuoteUseCase
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.ThreadLocal
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider
import org.kodein.di.erased.singleton

@ThreadLocal
val Injector = Kodein {

  bind<CoroutineContext>() with provider { ApplicationDispatcher }

  bind<GetIndexesUseCase>() with singleton { AppGetIndexesUseCase(instance()) }

  bind<GetQuoteUseCase>() with singleton { AppGetQuoteUseCase(instance()) }

  bind<IndexesRepository>() with provider { AppIndexesRepository(instance()) }

  bind<QuoteRepository>() with provider { AppQuoteRepository(instance()) }

  bind<NetworkSource>() with provider { AppNetworkSource(instance()) }

  bind<FmpApi>() with provider { FmpApi() }
}