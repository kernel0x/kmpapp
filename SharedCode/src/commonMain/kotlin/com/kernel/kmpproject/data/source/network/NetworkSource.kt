package com.kernel.kmpproject.data.source.network

import com.kernel.kmpproject.data.entity.MajorIndexesListEntity
import com.kernel.kmpproject.data.entity.QuoteEntity

interface NetworkSource {
  suspend fun getMajorIndexesList(): MajorIndexesListEntity
  suspend fun getQuotes(symbol: String): List<QuoteEntity>
}