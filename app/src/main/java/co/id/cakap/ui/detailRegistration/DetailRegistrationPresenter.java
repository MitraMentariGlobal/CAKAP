package co.id.cakap.ui.detailRegistration;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.ItemSearchRegistrationData;
import co.id.cakap.data.ItemShopData;
import co.id.cakap.data.OperationUserStatusData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;

public class DetailRegistrationPresenter implements DetailRegistrationContract.UserActionListener {
    private static WeakReference<DetailRegistrationContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<ItemSearchRegistrationData> arrayList;

    public DetailRegistrationPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public DetailRegistrationPresenter() {

    }

    @Override
    public void setView(DetailRegistrationContract.View view) {
        mView = new WeakReference<>(view);
    }

    public DetailRegistrationContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData(String memberId) {

    }

    @Override
    public void getDataRecId(String param) {
        arrayList = new ArrayList<>();
        arrayList.add(new ItemSearchRegistrationData("0000012", "Bambang"));
        arrayList.add(new ItemSearchRegistrationData("0000013", "Dadang Garut"));
        arrayList.add(new ItemSearchRegistrationData("0000014", "Yanto Kuncir"));
        arrayList.add(new ItemSearchRegistrationData("0000015", "Mpo Juju"));
        arrayList.add(new ItemSearchRegistrationData("0000016", "Cak Dul"));
        arrayList.add(new ItemSearchRegistrationData("0000017", "Tohir Kumis"));
        arrayList.add(new ItemSearchRegistrationData("0000018", "Budi Doremi"));
        arrayList.add(new ItemSearchRegistrationData("0000019", "Ijul Ketoprak"));
        arrayList.add(new ItemSearchRegistrationData("0000020", "Haji Adi"));
        getView().openDialogSearchData(arrayList, 1);
    }

    @Override
    public void getDataSponsorId(String param) {
        arrayList = new ArrayList<>();
        arrayList.add(new ItemSearchRegistrationData("0000021", "Anaknya Bambang"));
        arrayList.add(new ItemSearchRegistrationData("0000022", "Anaknya Dadang Garut"));
        arrayList.add(new ItemSearchRegistrationData("0000023", "Anaknya Yanto Kuncir"));
        arrayList.add(new ItemSearchRegistrationData("0000024", "Anaknya Mpo Juju"));
        arrayList.add(new ItemSearchRegistrationData("0000025", "Anaknya Cak Dul"));
        arrayList.add(new ItemSearchRegistrationData("0000026", "Anaknya Tohir Kumis"));
        arrayList.add(new ItemSearchRegistrationData("0000027", "Anaknya Budi Doremi"));
        arrayList.add(new ItemSearchRegistrationData("0000028", "Anaknya Ijul Ketoprak"));
        arrayList.add(new ItemSearchRegistrationData("0000029", "Anaknya Haji Adi"));
        getView().openDialogSearchData(arrayList, 2);
    }
}
