package com.kernel.kmpproject.ui.indexes

import com.kernel.kmpproject.domain.model.Indexes
import com.kernel.kmpproject.domain.model.Quote

sealed class IndexesViewState
object Empty : IndexesViewState()
object Loading : IndexesViewState()
data class Error(val message: String) : IndexesViewState()
data class ShowMajorIndexes(val indexes: Indexes) : IndexesViewState()
data class ShowQuote(val quote: Quote) : IndexesViewState()