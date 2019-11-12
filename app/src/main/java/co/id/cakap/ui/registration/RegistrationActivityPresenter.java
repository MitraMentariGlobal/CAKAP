package co.id.cakap.ui.registration;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.RegistrationData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;

public class RegistrationActivityPresenter implements RegistrationActivityContract.UserActionListener {
    private static WeakReference<RegistrationActivityContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<RegistrationData> arrayList;

    public RegistrationActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public RegistrationActivityPresenter() {

    }

    @Override
    public void setView(RegistrationActivityContract.View view){
        mView = new WeakReference<>(view);
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new RegistrationData("Starter Kit Basic", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
        arrayList.add(new RegistrationData("Starter Kit Lengkap", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
        arrayList.add(new RegistrationData("Starter Kit Lengkap", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
        arrayList.add(new RegistrationData("Starter Kit Khusus V-Bless", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
        arrayList.add(new RegistrationData("Starter Kit Basic", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
        arrayList.add(new RegistrationData("Starter Kit Basic", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
        arrayList.add(new RegistrationData("Starter Kit Khusus V-Bless", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
        arrayList.add(new RegistrationData("Starter Kit Basic", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
        arrayList.add(new RegistrationData("Starter Kit Khusus V-Bless", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
        arrayList.add(new RegistrationData("Starter Kit Basic", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
        getView().setAdapter(arrayList);
    }

    public RegistrationActivityContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }
}
