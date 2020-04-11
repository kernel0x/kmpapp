package com.kernel.kmpproject.domain.usecase

import com.kernel.kmpproject.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface GetQuoteUseCase {
  suspend operator fun invoke(symbol: String): Flow<Quote>
}