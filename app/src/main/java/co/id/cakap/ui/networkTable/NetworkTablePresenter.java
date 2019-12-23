package co.id.cakap.ui.networkTable;

import java.util.ArrayList;

import co.id.cakap.data.ItemEbonusCard;
import co.id.cakap.data.NetworkTableData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;

public class NetworkTablePresenter implements NetworkTableContract.UserActionListener {
    private NetworkTableContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private ArrayList<NetworkTableData> arrayList;

    public NetworkTablePresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(NetworkTableContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new NetworkTableData("1", "0000012", "ALBERT PANGEMANAN", "DKI JAKARTA", "JAKARTA SELATAN", "0000011", "DTR*5", "Active", "No", "0", "0", "0", "8,031", "5,244,945"));
        arrayList.add(new NetworkTableData("1", "0000012", "ALBERT PANGEMANAN", "DKI JAKARTA", "JAKARTA SELATAN", "0000011", "DTR*5", "Active", "No", "0", "0", "0", "8,031", "5,244,945"));
        arrayList.add(new NetworkTableData("1", "0000012", "ALBERT PANGEMANAN", "DKI JAKARTA", "JAKARTA SELATAN", "0000011", "DTR*5", "Active", "No", "0", "0", "0", "8,031", "5,244,945"));
        arrayList.add(new NetworkTableData("1", "0000012", "ALBERT PANGEMANAN", "DKI JAKARTA", "JAKARTA SELATAN", "0000011", "DTR*5", "Active", "No", "0", "0", "0", "8,031", "5,244,945"));
        arrayList.add(new NetworkTableData("1", "0000012", "ALBERT PANGEMANAN", "DKI JAKARTA", "JAKARTA SELATAN", "0000011", "DTR*5", "Active", "No", "0", "0", "0", "8,031", "5,244,945"));
        arrayList.add(new NetworkTableData("1", "0000012", "ALBERT PANGEMANAN", "DKI JAKARTA", "JAKARTA SELATAN", "0000011", "DTR*5", "Active", "No", "0", "0", "0", "8,031", "5,244,945"));
        arrayList.add(new NetworkTableData("1", "0000012", "ALBERT PANGEMANAN", "DKI JAKARTA", "JAKARTA SELATAN", "0000011", "DTR*5", "Active", "No", "0", "0", "0", "8,031", "5,244,945"));
        arrayList.add(new NetworkTableData("1", "0000012", "ALBERT PANGEMANAN", "DKI JAKARTA", "JAKARTA SELATAN", "0000011", "DTR*5", "Active", "No", "0", "0", "0", "8,031", "5,244,945"));
        arrayList.add(new NetworkTableData("1", "0000012", "ALBERT PANGEMANAN", "DKI JAKARTA", "JAKARTA SELATAN", "0000011", "DTR*5", "Active", "No", "0", "0", "0", "8,031", "5,244,945"));
        arrayList.add(new NetworkTableData("1", "0000012", "ALBERT PANGEMANAN", "DKI JAKARTA", "JAKARTA SELATAN", "0000011", "DTR*5", "Active", "No", "0", "0", "0", "8,031", "5,244,945"));
        arrayList.add(new NetworkTableData("1", "0000012", "ALBERT PANGEMANAN", "DKI JAKARTA", "JAKARTA SELATAN", "0000011", "DTR*5", "Active", "No", "0", "0", "0", "8,031", "5,244,945"));
        arrayList.add(new NetworkTableData("1", "0000012", "ALBERT PANGEMANAN", "DKI JAKARTA", "JAKARTA SELATAN", "0000011", "DTR*5", "Active", "No", "0", "0", "0", "8,031", "5,244,945"));
        arrayList.add(new NetworkTableData("1", "0000012", "ALBERT PANGEMANAN", "DKI JAKARTA", "JAKARTA SELATAN", "0000011", "DTR*5", "Active", "No", "0", "0", "0", "8,031", "5,244,945"));
        arrayList.add(new NetworkTableData("1", "0000012", "ALBERT PANGEMANAN", "DKI JAKARTA", "JAKARTA SELATAN", "0000011", "DTR*5", "Active", "No", "0", "0", "0", "8,031", "5,244,945"));
        mView.setAdapter(arrayList);
    }
}
