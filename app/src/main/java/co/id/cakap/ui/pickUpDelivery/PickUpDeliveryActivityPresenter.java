package co.id.cakap.ui.pickUpDelivery;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import co.id.cakap.data.AddressData;
import co.id.cakap.data.KotaData;
import co.id.cakap.data.ProvinsiData;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseKota;
import co.id.cakap.network.ApiResponseProvinsi;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

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
        arrayList.add(new AddressData("165", "BEKASI", "12", "JAWA BARAT", "Jalan jalan di bekasi sebelah warung pecel lele", true));
        arrayList.add(new AddressData("163", "BANDUNG", "12", "JAWA BARAT", "Jalan jalan di Jakarta Timur sebelah tenda biru", false));
//        arrayList.add(new AddressData("Solo", "Jawa Tengah", "Jalan jalan di Yogyakarta sebelah indomaret", false));
//        arrayList.add(new AddressData("Malang", "Jawa Timur", "Jalan jalan di Yogyakarta sebelah indomaret", false));
//        arrayList.add(new AddressData("Bali", "Bali", "Jalan jalan di Yogyakarta sebelah indomaret", false));
//        arrayList.add(new AddressData("Bali", "Bali", "Jalan jalan di Yogyakarta sebelah indomaret", false));
//        arrayList.add(new AddressData("Bali", "Bali", "Jalan jalan di Yogyakarta sebelah indomaret", false));
        getView().setAdapter(arrayList);
    }

    @Override
    public void getProvinsi() {
        mDataModel.deleteProvinsiData();
        mMainRepository.getProvinsi()
                .subscribe(new ResourceSubscriber<ApiResponseProvinsi>() {
                    @Override
                    public void onNext(ApiResponseProvinsi apiResponseProvinsi) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseProvinsi.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            saveProvinsiData(apiResponseProvinsi.getData());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        String errorResponse = "";
                        t.printStackTrace();
                        if (t instanceof HttpException) {
                            ResponseBody responseBody = ((HttpException)t).response().errorBody();
                            errorResponse = Utils.getErrorMessage(responseBody);
                            Logger.e("error HttpException: " + errorResponse);
                        }

                        getView().hideProgressBar();
                        getView().setErrorResponse(errorResponse);
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("onComplete");
                    }
                });
    }

    @Override
    public void getKota(String id) {
        mDataModel.deleteKotaData();
        mMainRepository.getKota(id)
                .subscribe(new ResourceSubscriber<ApiResponseKota>() {
                    @Override
                    public void onNext(ApiResponseKota apiResponseKota) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseKota.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            saveKotaData(apiResponseKota.getData());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        String errorResponse = "";
                        t.printStackTrace();
                        if (t instanceof HttpException) {
                            ResponseBody responseBody = ((HttpException)t).response().errorBody();
                            errorResponse = Utils.getErrorMessage(responseBody);
                            Logger.e("error HttpException: " + errorResponse);
                        }

                        getView().hideProgressBar();
                        getView().setErrorResponse(errorResponse);
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("onComplete");
                    }
                });
    }

    private void saveProvinsiData(List<ProvinsiData> provinsiData) {
        ArrayList<String> provinsiName = new ArrayList<>();
        ArrayList<String> provinsiId = new ArrayList<>();

        provinsiName.add("-");
        provinsiId.add("-");

        for (int i = 0; i < provinsiData.size(); i++) {
            mDataModel.insertProvinsiData(provinsiData.get(i));
            provinsiId.add(provinsiData.get(i).getId());
            provinsiName.add(provinsiData.get(i).getName());
        }

        getView().setProvinsiData(provinsiName, provinsiId);
    }

    private void saveKotaData(List<KotaData> kotaData) {
        ArrayList<String> kotaName = new ArrayList<>();
        ArrayList<String> kotaId = new ArrayList<>();

        kotaName.add("-");
        kotaId.add("-");

        for (int i = 0; i < kotaData.size(); i++) {
            mDataModel.insertKotaData(kotaData.get(i));
            kotaId.add(kotaData.get(i).getId());
            kotaName.add(kotaData.get(i).getName());
        }

        getView().setKotaData(kotaName, kotaId);
    }
}
