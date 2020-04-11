package com.kernel.kmpproject.data.source.network

import com.kernel.kmpproject.data.entity.MajorIndexesListEntity
import com.kernel.kmpproject.data.entity.QuoteEntity

class AppNetworkSource(private val fmpApi: FmpApi) : NetworkSource {

  override suspend fun getMajorIndexesList() =
    MajorIndexesListEntity(emptyList())

  override suspend fun getQuotes(symbol: String) =
    listOf(
      QuoteEntity("", "", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0)
    )
}