package co.id.cakap.ui.reqInvoiceToBc.reqInvoiceToBcSuccess;

import java.util.ArrayList;

import co.id.cakap.data.CashbillSuccessData;
import co.id.cakap.data.ReqInvoiceToBcSuccessData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.cashbillSuccess.CashbillSuccessContract;

public class ReqInvoiceToBcSuccessPresenter implements ReqInvoiceToBcSuccessContract.UserActionListener {
    private static ReqInvoiceToBcSuccessContract.View mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<ReqInvoiceToBcSuccessData> arrayList;

    public ReqInvoiceToBcSuccessPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(ReqInvoiceToBcSuccessContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
        mView.setAdapter(mDataModel.getAllReqInvoiceToBcSuccessData());
    }
}
