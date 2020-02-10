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
        arrayList = new ArrayList<>();
        arrayList.add(new ReqInvoiceToBcSuccessData("BT01", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new ReqInvoiceToBcSuccessData("BT02", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new ReqInvoiceToBcSuccessData("BT03", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new ReqInvoiceToBcSuccessData("BT04", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new ReqInvoiceToBcSuccessData("BT05", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new ReqInvoiceToBcSuccessData("BT06", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new ReqInvoiceToBcSuccessData("BT07", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new ReqInvoiceToBcSuccessData("BT08", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new ReqInvoiceToBcSuccessData("BT09", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new ReqInvoiceToBcSuccessData("BT010", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        mView.setAdapter(arrayList);
    }
}
