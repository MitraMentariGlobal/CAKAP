package co.id.cakap.ui.omset;

import java.util.ArrayList;

import co.id.cakap.data.OmsetData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;

public class OmsetActivityPresenter implements OmsetActivityContract.UserActionListener {
    private OmsetActivityContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    private ArrayList<OmsetData> arrayList;

    public OmsetActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(OmsetActivityContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new OmsetData("BT01", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new OmsetData("BT02", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new OmsetData("BT03", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new OmsetData("BT04", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new OmsetData("BT05", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new OmsetData("BT06", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new OmsetData("BT07", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new OmsetData("BT08", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new OmsetData("BT09", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new OmsetData("BT10", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new OmsetData("BT11", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new OmsetData("BT12", "Blesstea Botol", "20", "10.000.000"));
        mView.setAdapter(arrayList);
    }
}
