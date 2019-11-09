package co.id.cakap.ui.feeBCMB;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;

public class FeeBcmbActivityPresenter implements FeeBcmbActivityContract.UserActionListener {
    private FeeBcmbActivityContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public FeeBcmbActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(FeeBcmbActivityContract.View view){
        mView = view;
    }
}
