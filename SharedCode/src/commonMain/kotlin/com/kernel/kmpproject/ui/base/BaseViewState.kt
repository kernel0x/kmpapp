package com.kernel.kmpproject.ui.base

sealed class BaseViewState
object Loading : BaseViewState()
object Done : BaseViewState()
data class Error(val message: String) : BaseViewState()