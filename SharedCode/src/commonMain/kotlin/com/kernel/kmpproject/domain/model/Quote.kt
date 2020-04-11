package com.kernel.kmpproject.domain.model

typealias Quotes = List<Quote>

data class Quote(
  val dayLow: String,
  val dayHigh: String,
  val yearHigh: String,
  val yearLow: String
)