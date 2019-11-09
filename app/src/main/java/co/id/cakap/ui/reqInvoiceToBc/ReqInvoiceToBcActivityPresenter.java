package co.id.cakap.ui.reqInvoiceToBc;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;

public class ReqInvoiceToBcActivityPresenter implements ReqInvoiceToBcActivityContract.UserActionListener {
    private ReqInvoiceToBcActivityContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public ReqInvoiceToBcActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(ReqInvoiceToBcActivityContract.View view){
        mView = view;
    }
}
