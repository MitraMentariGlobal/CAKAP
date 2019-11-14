package co.id.cakap.ui.pickUpDelivery;

import java.util.ArrayList;

import co.id.cakap.data.AddressData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;

public class PickUpDeliveryActivityPresenter implements PickUpDeliveryActivityContract.UserActionListener {
    private PickUpDeliveryActivityContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    private ArrayList<AddressData> arrayList;

    public PickUpDeliveryActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(PickUpDeliveryActivityContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new AddressData("Bekasi", "Jawa Barat", "Jalan jalan di bekasi sebelah warung pecel lele", false));
        arrayList.add(new AddressData("Jakarta Timur", "DKI Jakarta", "Jalan jalan di Jakarta Timur sebelah tenda biru", false));
        arrayList.add(new AddressData("Yogyakarta", "Jawa Tengah", "Jalan jalan di Yogyakarta sebelah indomaret", false));
        arrayList.add(new AddressData("Yogyakarta", "Jawa Tengah", "Jalan jalan di Yogyakarta sebelah indomaret", false));
        arrayList.add(new AddressData("Yogyakarta", "Jawa Tengah", "Jalan jalan di Yogyakarta sebelah indomaret", false));
        arrayList.add(new AddressData("Yogyakarta", "Jawa Tengah", "Jalan jalan di Yogyakarta sebelah indomaret", false));
        arrayList.add(new AddressData("Yogyakarta", "Jawa Tengah", "Jalan jalan di Yogyakarta sebelah indomaret", false));
        mView.setAdapter(arrayList);
    }
}
