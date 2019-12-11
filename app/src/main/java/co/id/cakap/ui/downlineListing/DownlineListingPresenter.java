package co.id.cakap.ui.downlineListing;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;

public class DownlineListingPresenter implements DownlineListingContract.UserActionListener {
    private DownlineListingContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public DownlineListingPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(DownlineListingContract.View view){
        mView = view;
    }
}
