package co.id.cakap.ui.dashboard.restock.restockReqInvoice;

import java.util.ArrayList;

import co.id.cakap.data.RestockReqInvoiceData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.restock.restockInvoice.RestockInvoiceContract;

public class RestockReqInvoicePresenter implements RestockReqInvoiceContract.UserActionListener {
    private static RestockReqInvoiceContract.View mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<RestockReqInvoiceData> arrayList;

    public RestockReqInvoicePresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(RestockReqInvoiceContract.View view){
        mView = view;
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
        mView.setAdapter(arrayList);
    }
}
