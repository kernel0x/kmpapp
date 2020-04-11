package com.kernel.kmpproject.domain.usecase

import com.kernel.kmpproject.data.repository.IndexesRepository
import com.kernel.kmpproject.domain.model.Indexes
import kotlinx.coroutines.flow.Flow

class AppGetIndexesUseCase(
  private val indexRepository: IndexesRepository
) : GetIndexesUseCase {

  override suspend fun invoke(): Flow<Indexes> = indexRepository.getMajor()
}