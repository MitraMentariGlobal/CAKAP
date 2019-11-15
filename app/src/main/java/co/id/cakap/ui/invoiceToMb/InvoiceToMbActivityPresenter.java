package co.id.cakap.ui.invoiceToMb;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.ItemShopData;
import co.id.cakap.data.OperationUserStatusData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;

public class InvoiceToMbActivityPresenter implements InvoiceToMbActivityContract.UserActionListener {
    private static WeakReference<InvoiceToMbActivityContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private OperationUserStatusData operationUserStatusData;
    private ArrayList<ItemShopData> arrayList;

    public InvoiceToMbActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public InvoiceToMbActivityPresenter() {

    }

    @Override
    public void setView(InvoiceToMbActivityContract.View view) {
        mView = new WeakReference<>(view);
    }

    public InvoiceToMbActivityContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData(String memberId) {
        operationUserStatusData = new OperationUserStatusData(memberId, "Nama MB 1", "Active");

        arrayList = new ArrayList<>();
        arrayList.add(new ItemShopData("BT01", "Blesstea Botol", "0", "35", "Wilayah I", "115.000", "20", "0", "0"));
        arrayList.add(new ItemShopData("BT02", "Blesstea Sachet", "0", "46", "Wilayah I", "95.000", "20", "0", "0"));
        arrayList.add(new ItemShopData("BT04", "Blesstea Pouch", "0", "100", "Wilayah I", "80.000", "20", "0", "0"));
        arrayList.add(new ItemShopData("PC05", "Blesstea Teessiu Sachet", "0", "33", "Wilayah I", "72.000", "20", "0", "0"));
        arrayList.add(new ItemShopData("PC06", "Blesstea Bellesha Body Shower Pink with Camellia", "3", "100", "Wilayah I", "47.000", "20", "0", "0"));
        arrayList.add(new ItemShopData("PC07", "Blesstea Bellesha Body Shower Camellia", "66", "100", "Wilayah I", "47.000", "20", "0", "0"));
        arrayList.add(new ItemShopData("PC08", "Blesstea Bellesha Shampoo", "120", "100", "Wilayah I", "60.000", "20", "0", "0"));
        arrayList.add(new ItemShopData("PC09", "V-Bless Pantyliner", "0", "87", "Wilayah I", "37.000", "20", "0", "0"));
        arrayList.add(new ItemShopData("PC10", "V-Bless Day", "0", "9", "Wilayah I", "40.000", "20", "0", "0"));
        arrayList.add(new ItemShopData("PC11", "V-Bless Nite", "0", "7", "Wilayah I", "41.000", "20", "0", "0"));
        getView().setAdapter(arrayList, operationUserStatusData);
    }
}
