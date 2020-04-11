package com.kernel.kmpproject.domain.model

typealias Indexes = List<Index>

data class Index(
  val ticker: String,
  val changes: Float,
  val price: Float,
  val indexName: String
)