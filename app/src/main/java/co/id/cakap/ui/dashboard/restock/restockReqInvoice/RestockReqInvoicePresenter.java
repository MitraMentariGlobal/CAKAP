package co.id.cakap.ui.dashboard.restock.restockReqInvoice;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.restock.restockInvoice.RestockInvoiceContract;

public class RestockReqInvoicePresenter implements RestockReqInvoiceContract.UserActionListener {
    private RestockReqInvoiceContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public RestockReqInvoicePresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(RestockReqInvoiceContract.View view){
        mView = view;
    }
}
