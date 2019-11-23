package co.id.cakap.ui.stockReport.stockCard;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.RestockReqInvoiceData;
import co.id.cakap.data.StockCardData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.restock.restockReqInvoice.RestockReqInvoiceContract;

public class StockCardPresenter implements StockCardContract.UserActionListener {
    private static WeakReference<StockCardContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<StockCardData> arrayList;

    public StockCardPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public StockCardPresenter() {

    }

    public void setView(StockCardContract.View view){
        mView = new WeakReference<>(view);
    }

    public StockCardContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
//        arrayList.add(new StockCardData("INV - 123123123123123", "IDR 100.000.000", "123", "28 Jan 2020", "Pending"));
        getView().setAdapter(arrayList);
    }
}
