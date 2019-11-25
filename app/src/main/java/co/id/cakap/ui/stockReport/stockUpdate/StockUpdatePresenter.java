package co.id.cakap.ui.stockReport.stockUpdate;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.StockCardData;
import co.id.cakap.data.StockUpdateData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.stockReport.stockCard.StockCardContract;

public class StockUpdatePresenter implements StockUpdateContract.UserActionListener {
    private static WeakReference<StockUpdateContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<StockUpdateData> arrayList;

    public StockUpdatePresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public StockUpdatePresenter() {

    }

    public void setView(StockUpdateContract.View view){
        mView = new WeakReference<>(view);
    }

    public StockUpdateContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new StockUpdateData("BT01", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new StockUpdateData("BT02", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new StockUpdateData("BT03", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new StockUpdateData("BT04", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new StockUpdateData("BT05", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new StockUpdateData("BT06", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new StockUpdateData("BT07", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new StockUpdateData("BT08", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new StockUpdateData("BT09", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new StockUpdateData("BT10", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new StockUpdateData("BT11", "Blesstea Botol", "20", "10.000.000"));
        arrayList.add(new StockUpdateData("BT12", "Blesstea Botol", "20", "10.000.000"));
        getView().setAdapter(arrayList);
    }
}
