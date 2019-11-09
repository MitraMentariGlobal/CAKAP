package co.id.cakap.ui.omset;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;

public class OmsetActivityPresenter implements OmsetActivityContract.UserActionListener {
    private OmsetActivityContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public OmsetActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(OmsetActivityContract.View view){
        mView = view;
    }
}
