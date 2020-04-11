package com.kernel.kmpproject.domain.usecase

import com.kernel.kmpproject.domain.model.Indexes
import kotlinx.coroutines.flow.Flow

interface GetIndexesUseCase {
  suspend operator fun invoke(): Flow<Indexes>
}