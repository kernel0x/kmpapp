package com.kernel.kmpproject

import android.os.Looper
import com.kernel.kmpproject.domain.model.Quote
import com.kernel.kmpproject.ui.indexes.IndexesViewModel
import com.kernel.kmpproject.ui.indexes.ShowMajorIndexes
import com.kernel.kmpproject.ui.indexes.ShowQuote
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.fail

class IndexesViewModelTest {

  private lateinit var viewModel: IndexesViewModel

  @BeforeTest
  fun setUp() {
    val mockMainThreadLooper = mockk<Looper>()
    every { mockMainThreadLooper.thread } returns Thread.currentThread()
    mockkStatic(Looper::class)
    every { Looper.getMainLooper() } returns mockMainThreadLooper
    viewModel = IndexesViewModel()
  }

  @Test
  fun `Check state ShowMajorIndexes`() {
    viewModel.getViewData.addObserver {
      when (it) {
        is Error -> {
          fail("State is Error!")
        }
        is ShowMajorIndexes -> {
          assertTrue(it.indexes.isEmpty())
        }
      }
    }
    viewModel.getMajorIndexes()
    viewModel.getViewData.value = ShowMajorIndexes(emptyList())
  }

  @Test
  fun `Check state ShowQuote`() {
    viewModel.getViewData.addObserver {
      when (it) {
        is Error -> {
          fail("State is Error!")
        }
        is ShowQuote -> {
          assertTrue(it.quote.dayLow.isEmpty())
        }
      }
    }
    viewModel.getQuote("")
    viewModel.getViewData.value = ShowQuote(Quote("", "", "", ""))
  }
}