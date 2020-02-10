package co.id.cakap.ui.reqInvoiceToBc;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.ItemShopCompanyData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;

public class ReqInvoiceToBcActivityPresenter implements ReqInvoiceToBcActivityContract.UserActionListener {
    private static WeakReference<ReqInvoiceToBcActivityContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<ItemShopCompanyData> arrayList;

    public ReqInvoiceToBcActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public ReqInvoiceToBcActivityPresenter() {

    }

    @Override
    public void setView(ReqInvoiceToBcActivityContract.View view) {
        mView = new WeakReference<>(view);
    }

    public ReqInvoiceToBcActivityContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new ItemShopCompanyData("BT01", "Blesstea Botol", "0", "115.000", "20", "0", "0"));
        arrayList.add(new ItemShopCompanyData("BT02", "Blesstea Sachet", "0", "95.000", "20", "0", "0"));
        arrayList.add(new ItemShopCompanyData("BT04", "Blesstea Pouch", "0", "80.000", "20", "0", "0"));
        arrayList.add(new ItemShopCompanyData("PC05", "Blesstea Teessiu Sachet", "0", "72.000", "20", "0", "0"));
        arrayList.add(new ItemShopCompanyData("PC06", "Blesstea Bellesha Body Shower Pink with Camellia", "3", "47.000", "20", "0", "0"));
        arrayList.add(new ItemShopCompanyData("PC07", "Blesstea Bellesha Body Shower Camellia", "66", "47.000", "20", "0", "0"));
        arrayList.add(new ItemShopCompanyData("PC08", "Blesstea Bellesha Shampoo", "120", "60.000", "20", "0", "0"));
        arrayList.add(new ItemShopCompanyData("PC09", "V-Bless Pantyliner", "0", "37.000", "20", "0", "0"));
        arrayList.add(new ItemShopCompanyData("PC10", "V-Bless Day", "0", "40.000", "20", "0", "0"));
        arrayList.add(new ItemShopCompanyData("PC11", "V-Bless Nite", "0", "41.000", "20", "0", "0"));
        getView().setAdapter(arrayList);
    }
}
