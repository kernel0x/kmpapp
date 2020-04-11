import UIKit
import SharedCode

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
    
    internal func update(list: [Index]) {
        indexes.removeAll()
        indexes.append(contentsOf: list)
        tableView.reloadData()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return indexes.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "indexListCell", for: indexPath)
        let item = indexes[indexPath.row]
    
        cell.textLabel?.text = item.ticker + " " + item.indexName + " " + item.price.description + " " + item.changes.description
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        viewModel.getQuote(symbol: indexes[indexPath.row].ticker)
    }
    
    deinit {
        viewModel.onCleared()
    }
}
