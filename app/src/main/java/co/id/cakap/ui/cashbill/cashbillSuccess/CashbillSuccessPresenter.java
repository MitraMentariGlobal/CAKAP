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
        mView.setAdapter(mDataModel.getAllCashbillSuccessDetailData());
    }
}
