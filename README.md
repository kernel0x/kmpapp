# Kotlin Mobile Multiplatform App (Android & iOS)

One Code To Rule Them All. Application example using Kotlin Multiplatform and MVVM pattern for both platforms. 

<img src="https://github.com/kernel0x/kmpapp/blob/master/images/architecture.svg">

Is used:
- layered clean architecture
- DI (Kodein)
- coroutines
- livedata
- ktor
- serialization
- mockk
- detekt, ktlint
- unit tests and jacoco

### Presentation layer (Android & iOS)

On both platforms (Android and iOS), we only need to implement an observer in which to process states. Further implementation on Android (Kotlin) and iOS (Swift):

#### Android
```kotlin
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
```
#### iOS
```swift
class ViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    @IBOutlet weak var tableView: UITableView!
    
    private var viewModel: IndexesViewModel!
    
    internal var indexes: [Index] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tableView.dataSource = self
        tableView.delegate = self
        
        viewModel = IndexesViewModel()
        observeViewState()
        
        viewModel.getMajorIndexes()
    }
    
    func observeViewState() {
        viewModel.getViewData.addObserver { (state) in
            self.updateViewState(state: state as! IndexesViewState)
        }
    }
    
    func updateViewState(state: IndexesViewState) {
        switch state {
        case is Loading:
            view.displayToast("Loading...")
        case is Error:
            view.displayToast("Error")
        case is ShowMajorIndexes:
            let successState = state as! ShowMajorIndexes
            update(list: successState.indexes)
        case is ShowQuote:
            let successState = state as! ShowQuote
            view.displayToast(successState.quote.dayLow + " - " + successState.quote.dayHigh)
        default: break
        }
    }
    
    ...

    deinit {
        viewModel.onCleared()
    }
}
```

### Presentation Layer (Shared Code)

This layer is shared by Android and iOS, and this is developed on Kotlin. Here is where we have to call the different use-cases of the domain layer. To make the call async we are using kotlin coroutines and flow.

```kotlin
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
```

### Domain Layer — Model & UseCases (Shared Code)

In this layer, we defining the models and all the use cases that we need for our application.

### Data Layer — Repository Pattern (Shared Code)

For this layer we are using a repository pattern. We defining the entity models and all source of our data

For networking we are using Ktor and for JSON deserialisation Kotlinx serialization.

### Running unit tests

- Android test: ./gradlew testDebugUnitTest
- Common test on iOS (need run Simulator iPhone 8): ./gradlew iosUnitTest

### Running the app

To run the application use the same tools you use in Android and iOS. Just open the project with Intellj/Android Studio for the Android project and XCode for the iOS one.

## Screenshots

|Android|iOS|
|---|---|
|![android-app](https://github.com/kernel0x/kmpapp/blob/master/images/Screenshot_android.png)|![ios-app](https://github.com/kernel0x/kmpapp/blob/master/images/Screenshot_ios.png)|
