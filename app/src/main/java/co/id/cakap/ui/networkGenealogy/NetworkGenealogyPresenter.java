package co.id.cakap.ui.networkGenealogy;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;

public class NetworkGenealogyPresenter implements NetworkGenealogyContract.UserActionListener {
    private NetworkGenealogyContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public NetworkGenealogyPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(NetworkGenealogyContract.View view){
        mView = view;
    }
}
