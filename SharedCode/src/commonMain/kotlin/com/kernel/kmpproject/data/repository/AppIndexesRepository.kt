package com.kernel.kmpproject.data.repository

import com.kernel.kmpproject.data.entity.toDomain
import com.kernel.kmpproject.data.source.network.NetworkSource
import com.kernel.kmpproject.domain.model.Indexes
import com.kernel.kmpproject.logInfo
import com.kernel.kmpproject.utils.coroutines.timer
import kotlin.time.ExperimentalTime
import kotlin.time.seconds
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppIndexesRepository(networkSource: NetworkSource) : AppBaseRepository(networkSource), IndexesRepository {

  private companion object {
    @ExperimentalTime
    val updateInterval = 5.seconds
  }

  override suspend fun getMajor(): Flow<Indexes> {
    return timer(repeatEvery = updateInterval)
      .map {
        logInfo("Indexes", networkSource.getMajorIndexesList().majorIndexesList.size.toString())
        networkSource.getMajorIndexesList().majorIndexesList.toDomain()
      }
  }
}