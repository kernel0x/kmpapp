package com.kernel.kmpproject.ui.indexes

import com.kernel.kmpproject.di.Injector
import com.kernel.kmpproject.domain.usecase.GetIndexesUseCase
import com.kernel.kmpproject.domain.usecase.GetQuoteUseCase
import com.kernel.kmpproject.flowOnBackground
import com.kernel.kmpproject.launchInMain
import com.kernel.kmpproject.ui.base.BaseViewModel
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import org.kodein.di.erased.instance

class IndexesViewModel : BaseViewModel() {

  private val getIndexesUseCase by Injector.instance<GetIndexesUseCase>()
  private val getQuoteUseCase by Injector.instance<GetQuoteUseCase>()

  var getViewData = MutableLiveData<IndexesViewState>(Empty)

  fun getMajorIndexes() = launchInMain {
    getIndexesUseCase()
      .onStart { getViewData.postValue(Loading) }
      .flowOnBackground()
      .catch { getViewData.postValue(Error("Something went wrong")) }
      .collect { getViewData.postValue(ShowMajorIndexes(it)) }
  }

  fun getQuote(symbol: String) = launchInMain {
    getQuoteUseCase.invoke(symbol)
      .onStart { getViewData.postValue(Loading) }
      .flowOnBackground()
      .catch { getViewData.postValue(Error("Something went wrong")) }
      .collect { getViewData.postValue(ShowQuote(it)) }
  }
}