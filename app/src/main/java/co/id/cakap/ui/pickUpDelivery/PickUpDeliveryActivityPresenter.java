package co.id.cakap.ui.pickUpDelivery;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.AddressData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;

public class PickUpDeliveryActivityPresenter implements PickUpDeliveryActivityContract.UserActionListener {
    private static WeakReference<PickUpDeliveryActivityContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<AddressData> arrayList;

    public PickUpDeliveryActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public PickUpDeliveryActivityPresenter() {

    }

    @Override
    public void setView(PickUpDeliveryActivityContract.View view){
        mView = new WeakReference<>(view);
    }

    public PickUpDeliveryActivityContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new AddressData("Bekasi", "Jawa Barat", "Jalan jalan di bekasi sebelah warung pecel lele", true));
        arrayList.add(new AddressData("Bandung", "Jawa Barat", "Jalan jalan di Jakarta Timur sebelah tenda biru", false));
        arrayList.add(new AddressData("Solo", "Jawa Tengah", "Jalan jalan di Yogyakarta sebelah indomaret", false));
        arrayList.add(new AddressData("Malang", "Jawa Timur", "Jalan jalan di Yogyakarta sebelah indomaret", false));
        arrayList.add(new AddressData("Bali", "Bali", "Jalan jalan di Yogyakarta sebelah indomaret", false));
        arrayList.add(new AddressData("Bali", "Bali", "Jalan jalan di Yogyakarta sebelah indomaret", false));
        arrayList.add(new AddressData("Bali", "Bali", "Jalan jalan di Yogyakarta sebelah indomaret", false));
        getView().setAdapter(arrayList);
    }
}
