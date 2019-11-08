package co.id.cakap.ui.dashboard.restock.restockInvoice;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.RestockInvoiceData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillContract;

public class RestockInvoicePresenter implements RestockInvoiceContract.UserActionListener {
    private static WeakReference<RestockInvoiceContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<RestockInvoiceData> arrayList;

    public RestockInvoicePresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public RestockInvoicePresenter() {

    }

    public void setView(RestockInvoiceContract.View view){
        mView = new WeakReference<>(view);
    }

    public RestockInvoiceContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new RestockInvoiceData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020", "Pending"));
        arrayList.add(new RestockInvoiceData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020", "On going"));
        arrayList.add(new RestockInvoiceData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020", "Pending"));
        arrayList.add(new RestockInvoiceData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020", "On going"));
        arrayList.add(new RestockInvoiceData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020", "On going"));
        arrayList.add(new RestockInvoiceData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020", "Delivered"));
        arrayList.add(new RestockInvoiceData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020", "Delivered"));
        arrayList.add(new RestockInvoiceData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020", "Pending"));
        arrayList.add(new RestockInvoiceData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020", "Delivered"));
        arrayList.add(new RestockInvoiceData("INV - 789789789789789", "IDR 100.000.000", "123", "28 Jan 2020", "Pending"));
        getView().setAdapter(arrayList);
    }
}
