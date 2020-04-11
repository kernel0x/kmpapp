package com.kernel.kmpproject.ui.base

import com.kernel.kmpproject.di.Injector
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import org.kodein.di.erased.instance

open class BaseViewModel : ViewModel(), CoroutineScope {

  override val coroutineContext by Injector.instance<CoroutineContext>()
}