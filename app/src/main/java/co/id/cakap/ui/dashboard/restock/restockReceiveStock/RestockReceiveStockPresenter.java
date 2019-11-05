package co.id.cakap.ui.dashboard.restock.restockReceiveStock;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.restock.restockInvoice.RestockInvoiceContract;

public class RestockReceiveStockPresenter implements RestockReceiveStockContract.UserActionListener {
    private RestockReceiveStockContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public RestockReceiveStockPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(RestockReceiveStockContract.View view){
        mView = view;
    }
}
