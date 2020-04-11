package com.kernel.kmpproject.data.repository

import com.kernel.kmpproject.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
  suspend fun getQuoteIndex(symbol: String): Flow<Quote>
}