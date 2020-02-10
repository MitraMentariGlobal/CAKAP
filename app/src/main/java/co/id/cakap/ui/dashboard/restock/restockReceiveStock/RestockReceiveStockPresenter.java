package co.id.cakap.ui.dashboard.restock.restockReceiveStock;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.RestockReceiveStockData;
import co.id.cakap.data.RestockReqInvoiceData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.restock.restockInvoice.RestockInvoiceContract;

public class RestockReceiveStockPresenter implements RestockReceiveStockContract.UserActionListener {
    private static WeakReference<RestockReceiveStockContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<RestockReceiveStockData> arrayList;

    public RestockReceiveStockPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public RestockReceiveStockPresenter() {

    }

    public void setView(RestockReceiveStockContract.View view){
        mView = new WeakReference<>(view);
    }

    public RestockReceiveStockContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new RestockReceiveStockData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new RestockReceiveStockData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new RestockReceiveStockData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new RestockReceiveStockData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new RestockReceiveStockData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new RestockReceiveStockData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new RestockReceiveStockData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new RestockReceiveStockData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new RestockReceiveStockData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new RestockReceiveStockData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020"));
        getView().setAdapter(arrayList);
    }
}
