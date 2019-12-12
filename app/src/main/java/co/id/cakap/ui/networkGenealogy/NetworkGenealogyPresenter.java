package co.id.cakap.ui.networkGenealogy;

import co.id.cakap.data.NetworkGenealogyData;
import co.id.cakap.data.OperationUserStatusData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;

public class NetworkGenealogyPresenter implements NetworkGenealogyContract.UserActionListener {
    private NetworkGenealogyContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private NetworkGenealogyData networkGenealogyData;

    public NetworkGenealogyPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(NetworkGenealogyContract.View view){
        mView = view;
    }


    @Override
    public void getData(String memberId) {
        networkGenealogyData = new NetworkGenealogyData(memberId, "Nama MB 1");
        mView.setData(networkGenealogyData);
    }
}
