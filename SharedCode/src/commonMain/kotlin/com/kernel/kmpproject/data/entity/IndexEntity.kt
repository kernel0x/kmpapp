package com.kernel.kmpproject.data.entity

import com.kernel.kmpproject.domain.model.Index
import com.kernel.kmpproject.domain.model.Indexes
import kotlinx.serialization.Serializable

@Serializable
data class IndexEntity(
  val ticker: String,
  val changes: Float,
  val price: Float,
  val indexName: String
)

@Serializable
data class MajorIndexesListEntity(
  val majorIndexesList: List<IndexEntity>
)

internal fun List<IndexEntity>.toDomain(): Indexes = map { it.toDomain() }

internal fun IndexEntity.toDomain(): Index = Index(
  ticker = ticker,
  changes = changes,
  price = price,
  indexName = indexName
)