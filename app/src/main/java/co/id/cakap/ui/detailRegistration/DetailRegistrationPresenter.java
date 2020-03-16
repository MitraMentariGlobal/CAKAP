package co.id.cakap.ui.detailRegistration;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import co.id.cakap.data.BankData;
import co.id.cakap.data.ItemSearchRegistrationData;
import co.id.cakap.data.ItemShopData;
import co.id.cakap.data.KotaData;
import co.id.cakap.data.OperationUserStatusData;
import co.id.cakap.data.ProvinsiData;
import co.id.cakap.data.ReligionData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseBank;
import co.id.cakap.network.ApiResponseItemSearchRegistration;
import co.id.cakap.network.ApiResponseKota;
import co.id.cakap.network.ApiResponseProvinsi;
import co.id.cakap.network.ApiResponseRegistrationList;
import co.id.cakap.network.ApiResponseReligion;
import co.id.cakap.network.ApiResponseSubmitRegistration;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class DetailRegistrationPresenter implements DetailRegistrationContract.UserActionListener {
    private static WeakReference<DetailRegistrationContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private static ResultDataLogin mResultDataLogin;

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
    public void getData(String memberId, int idFrom) {
        getView().showProgressBar();

        mMainRepository.postItemSearchRegistration(memberId)
                .subscribe(new ResourceSubscriber<ApiResponseItemSearchRegistration>() {
                    @Override
                    public void onNext(ApiResponseItemSearchRegistration apiResponseItemSearchRegistration) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseItemSearchRegistration.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            if (apiResponseItemSearchRegistration.getData().isEmpty()) {
                                getView().hideProgressBar();
                                getView().setErrorResponse(apiResponseItemSearchRegistration.getMessages());
                            } else {
                                getView().openDialogSearchData(apiResponseItemSearchRegistration.getData(), idFrom);
                            }
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
    public void getDataRecId(String param) {
        getData(param, 1);
    }

    @Override
    public void getDataSponsorId(String param) {
        getData(param, 2);
    }

    @Override
    public void getReligion() {
        getView().showProgressBar();

        mDataModel.deleteReligionData();
        mMainRepository.getReligion()
                .subscribe(new ResourceSubscriber<ApiResponseReligion>() {
                    @Override
                    public void onNext(ApiResponseReligion apiResponseReligion) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseReligion.getData());
                        Logger.d("<<<<<=====");

                        try {
                            saveReligionData(apiResponseReligion.getData());
                            getProvinsi();
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
        mDataModel.deleteProvinsiData();
        mMainRepository.getProvinsi()
                .subscribe(new ResourceSubscriber<ApiResponseProvinsi>() {
                    @Override
                    public void onNext(ApiResponseProvinsi apiResponseProvinsi) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseProvinsi.getData());
                        Logger.d("<<<<<=====");

                        try {
                            saveProvinsiData(apiResponseProvinsi.getData());
                            getBank();
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
                        Logger.d("message : " + apiResponseKota.getData());
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

    @Override
    public void getBank() {
        mDataModel.deleteBankData();
        mMainRepository.getBank()
                .subscribe(new ResourceSubscriber<ApiResponseBank>() {
                    @Override
                    public void onNext(ApiResponseBank apiResponseBank) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseBank.getData());
                        Logger.d("<<<<<=====");

                        try {
                            saveBankData(apiResponseBank.getData());
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
    public void checkData(String recId, String sponsorId, String name, String ktp, String religion, String alamat,
                          String provinsi, String kota, String pewaris, String hubungan) {

        boolean isCheckRecId = false;
        boolean isCheckSponsorId = false;
        boolean isCheckNama = false;
        boolean isChecKtp = false;
        boolean isChecReligion = false;
        boolean isChecAlamat = false;
        boolean isCheckProvinsi = false;
        boolean isCheckKota = false;
        boolean isCheckNamaPewaris = false;
        boolean isCheckHubungan = false;

        if (recId.length() == 0) {
            getView().setErrorRecId(true);
        } else {
            getView().setErrorRecId(false);
            isCheckRecId = true;
        }

        if (sponsorId.length() == 0) {
            getView().setErrorSponsorId(true);
        } else {
            getView().setErrorSponsorId(false);
            isCheckSponsorId = true;
        }

        if (name.length() == 0) {
            getView().setErrorName(true);
        } else {
            getView().setErrorName(false);
            isCheckNama = true;
        }

        if (ktp.length() == 0) {
            getView().setErrorKtp(true);
        } else {
            getView().setErrorKtp(false);
            isChecKtp = true;
        }

        if (religion.equals("-")) {
            getView().setErrorReligion(true);
        } else {
            getView().setErrorReligion(false);
            isChecReligion = true;
        }

        if (alamat.length() == 0) {
            getView().setErrorAlamat(true);
        } else {
            getView().setErrorAlamat(false);
            isChecAlamat = true;
        }

        if (provinsi.equals("-")) {
            getView().setErrorProvinsi(true);
        } else {
            getView().setErrorProvinsi(false);
            isCheckProvinsi = true;
        }

        if (kota.equals("-")) {
            getView().setErrorKota(true);
        } else {
            getView().setErrorKota(false);
            isCheckKota = true;
        }

        if (pewaris.length() == 0) {
            getView().setErrorNamaPewaris(true);
        } else {
            getView().setErrorNamaPewaris(false);
            isCheckNamaPewaris = true;
        }

        if (hubungan.length() == 0) {
            getView().setErrorHubungan(true);
        } else {
            getView().setErrorHubungan(false);
            isCheckHubungan = true;
        }

        if (isCheckRecId && isCheckSponsorId && isCheckNama && isChecKtp && isChecReligion && isChecAlamat &&
                isCheckProvinsi && isCheckKota && isCheckNamaPewaris && isCheckHubungan) {
            getView().openConfirmationDialog();
        } else {
            getView().setErrorResponse("Some field are required!");
        }
    }

    @Override
    public void sendRegistrationData(
            String pin, String recId, String sponsorId, String memberId, String name, String ktp,
            String gender, String pob, String dob, String religion, String email, String noTelp,
            String noHp, String alamat, String kotaId, String kodePos, String activation, String pewaris,
            String hubungan, String bankId, String cabang, String namaNasabah, String norek) {

        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postRegistrationData(mResultDataLogin.getMember_id(), mResultDataLogin.getUsername(), pin,
                recId, sponsorId, memberId, name, ktp, gender, pob, DateHelper.changeToFormatBackend(dob), religion, email, noTelp, noHp, alamat,
                kotaId, kodePos, activation, pewaris, hubungan, bankId, cabang, namaNasabah, norek)
                .subscribe(new ResourceSubscriber<ApiResponseSubmitRegistration>() {
                    @Override
                    public void onNext(ApiResponseSubmitRegistration apiResponseSubmitRegistration) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseSubmitRegistration.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            getView().successInputData();
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

    private void saveReligionData(List<ReligionData> religionDataList) {
        ArrayList<String> religion = new ArrayList<>();

        for (int i = 0; i < religionDataList.size(); i++) {
            mDataModel.insertReligionData(religionDataList.get(i));
            religion.add(religionDataList.get(i).getAgama());
        }

        getView().setReligionData(religion);
    }

    private void saveBankData(List<BankData> bankDataList) {
        ArrayList<String> bankName = new ArrayList<>();
        ArrayList<String> bankId = new ArrayList<>();

        bankName.add("-");
        bankId.add("-");

        for (int i = 0; i < bankDataList.size(); i++) {
            mDataModel.insertBankData(bankDataList.get(i));
            bankId.add(bankDataList.get(i).getId());
            bankName.add(bankDataList.get(i).getName());
        }

        getView().setBankData(bankName, bankId);
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
