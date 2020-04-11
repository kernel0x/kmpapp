package com.kernel.kmpproject.data.entity

import com.kernel.kmpproject.domain.model.Quote
import com.kernel.kmpproject.domain.model.Quotes
import kotlinx.serialization.Serializable

@Serializable
data class QuoteEntity(
  val symbol: String,
  val name: String,
  val price: Double,
  val changesPercentage: Double,
  val change: Double,
  val dayLow: Double,
  val dayHigh: Double,
  val yearHigh: Double,
  val yearLow: Double,
  val timestamp: Long
)

internal fun List<QuoteEntity>.toDomain(): Quotes = map { it.toDomain() }

internal fun QuoteEntity.toDomain(): Quote = Quote(
  dayLow = dayLow.toString(),
  dayHigh = dayHigh.toString(),
  yearLow = yearLow.toString(),
  yearHigh = yearHigh.toString()
)