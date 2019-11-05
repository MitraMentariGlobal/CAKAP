package co.id.cakap.ui.dashboard.restock.restockInvoice;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillContract;

public class RestockInvoicePresenter implements RestockInvoiceContract.UserActionListener {
    private RestockInvoiceContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public RestockInvoicePresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(RestockInvoiceContract.View view){
        mView = view;
    }
}
