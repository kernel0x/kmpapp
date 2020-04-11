package com.kernel.kmpproject.data.source.network

import com.kernel.kmpproject.data.entity.MajorIndexesListEntity
import com.kernel.kmpproject.data.entity.QuoteEntity
import com.kernel.kmpproject.platformName
import io.ktor.client.HttpClient
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.userAgent
import kotlinx.serialization.internal.ArrayListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

private const val BASE_URL = "https://financialmodelingprep.com/api/v3"

class FmpApi {

  private val client: HttpClient by lazy {
    HttpClient {
      install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
      }
    }
  }

  suspend fun getMajorsIndexes(): MajorIndexesListEntity = client.get<String>("$BASE_URL/majors-indexes") {
    userAgent(platformName())
  }.let {
    Json(JsonConfiguration(strictMode = false)).parse(MajorIndexesListEntity.serializer(), it)
  }

  suspend fun getQuotes(symbol: String): List<QuoteEntity> =
    client.get<String>("$BASE_URL/quote/$symbol") {
      userAgent(platformName())
    }.let {
      Json(JsonConfiguration(strictMode = false)).parse(ArrayListSerializer(QuoteEntity.serializer()), it)
    }
}