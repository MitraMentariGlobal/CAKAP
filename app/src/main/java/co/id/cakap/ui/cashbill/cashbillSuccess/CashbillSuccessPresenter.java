package co.id.cakap.ui.cashbill.cashbillSuccess;

import java.util.ArrayList;

import co.id.cakap.data.CashbillSuccessData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;

public class CashbillSuccessPresenter implements CashbillSuccessContract.UserActionListener {
    private static CashbillSuccessContract.View mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<CashbillSuccessData> arrayList;

    public CashbillSuccessPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(CashbillSuccessContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new CashbillSuccessData("BT01", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new CashbillSuccessData("BT02", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new CashbillSuccessData("BT03", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new CashbillSuccessData("BT04", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new CashbillSuccessData("BT05", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new CashbillSuccessData("BT06", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new CashbillSuccessData("BT07", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new CashbillSuccessData("BT08", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new CashbillSuccessData("BT09", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        arrayList.add(new CashbillSuccessData("BT010", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        mView.setAdapter(arrayList);
    }
}
