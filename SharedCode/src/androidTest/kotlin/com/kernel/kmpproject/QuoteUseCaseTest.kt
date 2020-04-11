package com.kernel.kmpproject

import com.kernel.kmpproject.data.entity.QuoteEntity
import com.kernel.kmpproject.data.repository.AppQuoteRepository
import com.kernel.kmpproject.data.source.network.NetworkSource
import com.kernel.kmpproject.di.Injector
import com.kernel.kmpproject.domain.usecase.AppGetQuoteUseCase
import com.kernel.kmpproject.domain.usecase.GetQuoteUseCase
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.fail
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withTimeoutOrNull
import org.kodein.di.erased.instance

class QuoteUseCaseTest {

  private val getQuoteUseCase by Injector.instance<GetQuoteUseCase>()

  @Test
  fun `Check dayLow is zero`() {
    runBlocking {
      withTimeoutOrNull(250) {
        getQuoteUseCase("")
          .flowOnBackground()
          .catch { fail(it.message) }
          .collect {
            assertTrue(it.dayLow == "0.0")
          }
      }
    }
  }

  @Test
  fun `Check yearHigh is not zero`() {
    val networkSource = mockk<NetworkSource>()
    every { runBlocking { networkSource.getQuotes("") } } returns
      listOf(
        QuoteEntity("", "", 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0)
      )
    val quoteRepository = AppQuoteRepository(networkSource)
    val getQuoteUseCase = AppGetQuoteUseCase(quoteRepository)

    runBlocking {
      withTimeoutOrNull(250) {
        getQuoteUseCase("")
          .flowOnBackground()
          .catch { fail(it.message) }
          .collect {
            assertTrue(it.yearHigh != "0.0")
          }
      }
    }
  }
}