package co.id.cakap.ui.reqInvoiceToCompany;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;

public class ReqInvoiceToCompanyActivityPresenter implements ReqInvoiceToCompanyActivityContract.UserActionListener {
    private ReqInvoiceToCompanyActivityContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public ReqInvoiceToCompanyActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(ReqInvoiceToCompanyActivityContract.View view){
        mView = view;
    }
}
