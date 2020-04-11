package com.kernel.kmpproject.data.repository

import com.kernel.kmpproject.domain.model.Indexes
import kotlinx.coroutines.flow.Flow

interface IndexesRepository {
  suspend fun getMajor(): Flow<Indexes>
}