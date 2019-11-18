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
        arrayList = new ArrayList<>();
        arrayList.add(new InvoiceToMbSuccessData("BT01", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new InvoiceToMbSuccessData("BT02", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new InvoiceToMbSuccessData("BT03", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new InvoiceToMbSuccessData("BT04", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new InvoiceToMbSuccessData("BT05", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new InvoiceToMbSuccessData("BT06", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new InvoiceToMbSuccessData("BT07", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new InvoiceToMbSuccessData("BT08", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new InvoiceToMbSuccessData("BT09", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new InvoiceToMbSuccessData("BT010", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        mView.setAdapter(arrayList);
    }
}
