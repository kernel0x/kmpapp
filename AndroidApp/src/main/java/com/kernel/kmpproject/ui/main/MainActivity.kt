package com.kernel.kmpproject.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.kernel.kmpproject.R
import com.kernel.kmpproject.ui.indexes.*
import com.kernel.kmpproject.ui.main.adapter.IndexesAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  private lateinit var viewModel: IndexesViewModel
  private var adapter: IndexesAdapter = IndexesAdapter { viewModel.getQuote(it.ticker) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    recycler.adapter = adapter

    viewModel = ViewModelProviders.of(this).get(IndexesViewModel::class.java)
    observeViewState()

    viewModel.getMajorIndexes()
  }

  private fun observeViewState() {
    viewModel.getViewData.addObserver { updateViewState(it) }
  }

  private fun updateViewState(state: IndexesViewState) = runOnUiThread {
    when (state) {
      is Loading -> {
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
      }
      is Error -> {
        Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
      }
      is ShowMajorIndexes -> {
        adapter.items = state.indexes
      }
      is ShowQuote -> {
        Toast.makeText(this, state.quote.dayLow + " - " + state.quote.dayHigh, Toast.LENGTH_LONG).show()
      }
    }
  }
}
