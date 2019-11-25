package co.id.cakap.ui.feeBCMB;

import co.id.cakap.data.FeeBCMBData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;

public class FeeBcmbActivityPresenter implements FeeBcmbActivityContract.UserActionListener {
    private FeeBcmbActivityContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    private FeeBCMBData mFeeBCMBData;

    public FeeBcmbActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(FeeBcmbActivityContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
        mFeeBCMBData = new FeeBCMBData(
                "80,399,091",
                "66,717,273",
                "17 (paket)",
                "9 (paket)",
                "3,335,864",
                "0",
                "127,500",
                "450,000",
                "14,792,817",
                "0",
                "18,706,181"
        );

        mView.setData(mFeeBCMBData);
    }
}
