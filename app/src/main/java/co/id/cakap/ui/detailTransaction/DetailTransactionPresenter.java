package co.id.cakap.ui.detailTransaction;

import java.util.ArrayList;

import co.id.cakap.data.DetailTransactionData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;

public class DetailTransactionPresenter implements DetailTransactionContract.UserActionListener {
    private static DetailTransactionContract.View mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<DetailTransactionData> arrayList;

    public DetailTransactionPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(DetailTransactionContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
//        arrayList.add(new DetailTransactionData("BT01", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
//        arrayList.add(new DetailTransactionData("BT02", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
//        arrayList.add(new DetailTransactionData("BT03", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
//        arrayList.add(new DetailTransactionData("BT04", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
//        arrayList.add(new DetailTransactionData("BT05", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
//        arrayList.add(new DetailTransactionData("BT06", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
//        arrayList.add(new DetailTransactionData("BT07", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
//        arrayList.add(new DetailTransactionData("BT08", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
//        arrayList.add(new DetailTransactionData("BT09", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
//        arrayList.add(new DetailTransactionData("BT010", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
        mView.setAdapter(arrayList);
    }
}
