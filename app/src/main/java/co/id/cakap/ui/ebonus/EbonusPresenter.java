package co.id.cakap.ui.ebonus;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;

public class EbonusPresenter implements EbonusContract.UserActionListener {
    private EbonusContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public EbonusPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(EbonusContract.View view){
        mView = view;
    }
}
