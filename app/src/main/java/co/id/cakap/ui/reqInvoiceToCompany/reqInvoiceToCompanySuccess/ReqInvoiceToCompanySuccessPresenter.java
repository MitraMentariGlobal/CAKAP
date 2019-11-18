package co.id.cakap.ui.reqInvoiceToCompany.reqInvoiceToCompanySuccess;

import java.util.ArrayList;

import co.id.cakap.data.ReqInvoiceToBcSuccessData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.reqInvoiceToBc.reqInvoiceToBcSuccess.ReqInvoiceToBcSuccessContract;

public class ReqInvoiceToCompanySuccessPresenter implements ReqInvoiceToCompanySuccessContract.UserActionListener {
    private static ReqInvoiceToCompanySuccessContract.View mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    public ReqInvoiceToCompanySuccessPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(ReqInvoiceToCompanySuccessContract.View view){
        mView = view;
    }

    @Override
    public void getData() {

    }
}
