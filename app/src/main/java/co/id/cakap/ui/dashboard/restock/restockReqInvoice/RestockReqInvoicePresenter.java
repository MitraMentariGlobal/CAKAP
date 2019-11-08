package co.id.cakap.ui.dashboard.restock.restockReqInvoice;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.RestockReqInvoiceData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.restock.restockInvoice.RestockInvoiceContract;

public class RestockReqInvoicePresenter implements RestockReqInvoiceContract.UserActionListener {
    private static WeakReference<RestockReqInvoiceContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<RestockReqInvoiceData> arrayList;

    public RestockReqInvoicePresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public RestockReqInvoicePresenter() {

    }

    public void setView(RestockReqInvoiceContract.View view){
        mView = new WeakReference<>(view);
    }

    public RestockReqInvoiceContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new RestockReqInvoiceData("INV - 123123123123123", "IDR 100.000.000", "123", "28 Jan 2020", "Pending"));
        arrayList.add(new RestockReqInvoiceData("INV - 123123123123123", "IDR 100.000.000", "123", "28 Jan 2020", "On going"));
        arrayList.add(new RestockReqInvoiceData("INV - 123123123123123", "IDR 100.000.000", "123", "28 Jan 2020", "Pending"));
        arrayList.add(new RestockReqInvoiceData("INV - 123123123123123", "IDR 100.000.000", "123", "28 Jan 2020", "On going"));
        arrayList.add(new RestockReqInvoiceData("INV - 123123123123123", "IDR 100.000.000", "123", "28 Jan 2020", "On going"));
        arrayList.add(new RestockReqInvoiceData("INV - 123123123123123", "IDR 100.000.000", "123", "28 Jan 2020", "Delivered"));
        arrayList.add(new RestockReqInvoiceData("INV - 123123123123123", "IDR 100.000.000", "123", "28 Jan 2020", "Delivered"));
        arrayList.add(new RestockReqInvoiceData("INV - 123123123123123", "IDR 100.000.000", "123", "28 Jan 2020", "Pending"));
        arrayList.add(new RestockReqInvoiceData("INV - 123123123123123", "IDR 100.000.000", "123", "28 Jan 2020", "Delivered"));
        arrayList.add(new RestockReqInvoiceData("INV - 123123123123123", "IDR 100.000.000", "123", "28 Jan 2020", "Pending"));
        getView().setAdapter(arrayList);
    }
}
