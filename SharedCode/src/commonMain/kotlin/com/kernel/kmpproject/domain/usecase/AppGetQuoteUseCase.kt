package com.kernel.kmpproject.domain.usecase

import com.kernel.kmpproject.data.repository.QuoteRepository
import com.kernel.kmpproject.domain.model.Quote
import kotlinx.coroutines.flow.Flow

class AppGetQuoteUseCase(
  private val quoteRepository: QuoteRepository
) : GetQuoteUseCase {

  override suspend fun invoke(symbol: String): Flow<Quote> = quoteRepository.getQuoteIndex(symbol)
}