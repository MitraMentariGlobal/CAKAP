package co.id.cakap.ui.invoiceToMb;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;

public class InvoiceToMbActivityPresenter implements InvoiceToMbActivityContract.UserActionListener {
    private InvoiceToMbActivityContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public InvoiceToMbActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(InvoiceToMbActivityContract.View view){
        mView = view;
    }
}
