package com.kernel.kmpproject

import com.kernel.kmpproject.data.entity.IndexEntity
import com.kernel.kmpproject.data.entity.MajorIndexesListEntity
import com.kernel.kmpproject.data.repository.AppIndexesRepository
import com.kernel.kmpproject.data.source.network.NetworkSource
import com.kernel.kmpproject.di.Injector
import com.kernel.kmpproject.domain.usecase.AppGetIndexesUseCase
import com.kernel.kmpproject.domain.usecase.GetIndexesUseCase
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.fail
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withTimeoutOrNull
import org.kodein.di.erased.instance

class IndexesUseCaseTest {

  private val getIndexesUseCase by Injector.instance<GetIndexesUseCase>()

  @Test
  fun `Check indexes is empty`() {
    runBlocking {
      withTimeoutOrNull(250) {
        getIndexesUseCase()
          .flowOnBackground()
          .catch { fail(it.message) }
          .collect {
            assertTrue(it.isEmpty())
          }
      }
    }
  }

  @Test
  fun `Check indexes is not empty`() {
    val networkSource = mockk<NetworkSource>()
    every { runBlocking { networkSource.getMajorIndexesList() } } returns
      MajorIndexesListEntity(
        listOf(
          IndexEntity(".DJI", 28.7089f, 23719.4f, "Dow Jones")
        )
      )
    val indexesRepository = AppIndexesRepository(networkSource)
    val getIndexesUseCase = AppGetIndexesUseCase(indexesRepository)

    runBlocking {
      withTimeoutOrNull(250) {
        getIndexesUseCase()
          .flowOnBackground()
          .catch { fail(it.message) }
          .collect {
            assertTrue(it.isNotEmpty())
          }
      }
    }
  }
}