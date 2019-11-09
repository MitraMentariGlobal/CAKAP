package co.id.cakap.ui.cashbill;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.account.AccountContract;

public class CashbillActivityPresenter implements CashbillActivityContract.UserActionListener {
    private CashbillActivityContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public CashbillActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(CashbillActivityContract.View view){
        mView = view;
    }
}
