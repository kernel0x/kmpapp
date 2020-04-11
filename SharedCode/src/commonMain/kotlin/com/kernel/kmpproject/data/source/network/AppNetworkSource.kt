package com.kernel.kmpproject.data.source.network

class AppNetworkSource(private val fmpApi: FmpApi) : NetworkSource {
  override suspend fun getMajorIndexesList() = fmpApi.getMajorsIndexes()
  override suspend fun getQuotes(symbol: String) = fmpApi.getQuotes(symbol)
}