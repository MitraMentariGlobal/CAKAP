package co.id.cakap.ui.invoiceToMb.invoiceToMbSuccess;

import java.util.ArrayList;

import co.id.cakap.data.CashbillSuccessData;
import co.id.cakap.data.InvoiceToMbSuccessData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.cashbillSuccess.CashbillSuccessContract;

public class InvoiceToMbSuccessPresenter implements InvoiceToMbSuccessContract.UserActionListener {
    private static InvoiceToMbSuccessContract.View mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<InvoiceToMbSuccessData> arrayList;

    public InvoiceToMbSuccessPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(InvoiceToMbSuccessContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
        mView.setAdapter(mDataModel.getAllInvoiceToMbSuccessDetailData());
    }
}
