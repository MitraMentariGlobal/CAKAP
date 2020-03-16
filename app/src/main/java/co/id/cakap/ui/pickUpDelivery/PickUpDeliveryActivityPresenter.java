package co.id.cakap.ui.pickUpDelivery;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import co.id.cakap.data.AddressHistoryData;
import co.id.cakap.data.KotaData;
import co.id.cakap.data.ProvinsiData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseAddEditAddress;
import co.id.cakap.network.ApiResponseKota;
import co.id.cakap.network.ApiResponseListAddress;
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
    private static ResultDataLogin mResultDataLogin;

    private ArrayList<AddressHistoryData> arrayList;

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
//        arrayList = new ArrayList<>();
//        arrayList.add(new AddressHistoryData("165", "BEKASI", "Jalan jalan di bekasi sebelah warung pecel lele", "1", "JAWA BARAT"));
//        arrayList.add(new AddressHistoryData("193", "JAKARTA SELATAN", "Jalan jalan di Jakarta Timur sebelah tenda biru", "1", "DKI JAKARTA"));
//        arrayList.add(new AddressHistoryData("316", "KAPUAS HULU", "Jalan jalan di Jakarta Timur sebelah tenda biru", "1", "KALIMANTAN BARAT"));
//        getView().setAdapter(arrayList);

        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postListAlamat(mResultDataLogin.getMember_id())
//        mMainRepository.postListAlamat("0000160")
                .subscribe(new ResourceSubscriber<ApiResponseListAddress>() {
                    @Override
                    public void onNext(ApiResponseListAddress apiResponseListAddress) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseListAddress.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            getView().setAdapter(apiResponseListAddress.getData());
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
    public void addAddress(String alamat, String kotaId) {
        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postSubmitAlamat(mResultDataLogin.getMember_id(), alamat, kotaId, mResultDataLogin.getUsername())
                .subscribe(new ResourceSubscriber<ApiResponseAddEditAddress>() {
                    @Override
                    public void onNext(ApiResponseAddEditAddress apiResponseAddEditAddress) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseAddEditAddress.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            getView().setErrorResponse("Add Address Successfully");
                            getData();
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
    public void editAddress(String alamat, String kotaId, String idAlamat) {
        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postEditAlamat(mResultDataLogin.getMember_id(), alamat, kotaId, mResultDataLogin.getUsername(), idAlamat)
                .subscribe(new ResourceSubscriber<ApiResponseAddEditAddress>() {
                    @Override
                    public void onNext(ApiResponseAddEditAddress apiResponseAddEditAddress) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseAddEditAddress.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            getView().setErrorResponse("Change Address Successfully");
                            getData();
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
    public void getProvinsi() {
        getView().showProgressBar();

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
        getView().showProgressBar();

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
