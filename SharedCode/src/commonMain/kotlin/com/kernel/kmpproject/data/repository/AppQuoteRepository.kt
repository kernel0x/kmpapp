package com.kernel.kmpproject.data.repository

import com.kernel.kmpproject.data.entity.toDomain
import com.kernel.kmpproject.data.source.network.NetworkSource
import kotlinx.coroutines.flow.flow

class AppQuoteRepository(networkSource: NetworkSource) : AppBaseRepository(networkSource), QuoteRepository {

  override suspend fun getQuoteIndex(symbol: String) = flow { emit(networkSource.getQuotes(symbol).toDomain()[0]) }
}