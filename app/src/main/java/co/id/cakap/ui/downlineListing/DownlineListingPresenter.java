package co.id.cakap.ui.downlineListing;

import java.util.ArrayList;

import co.id.cakap.data.DownlineListingData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;

public class DownlineListingPresenter implements DownlineListingContract.UserActionListener {
    private DownlineListingContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private ArrayList<DownlineListingData> arrayList;

    public DownlineListingPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(DownlineListingContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        mView.setAdapter(arrayList);
    }
}
