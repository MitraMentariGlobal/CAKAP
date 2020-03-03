package co.id.cakap.ui.myProfile;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import co.id.cakap.data.BankData;
import co.id.cakap.data.JenisKelaminData;
import co.id.cakap.data.ReligionData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.helper.Constant;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseBank;
import co.id.cakap.network.ApiResponseChangePin;
import co.id.cakap.network.ApiResponseJenisKelamin;
import co.id.cakap.network.ApiResponseProfileData;
import co.id.cakap.network.ApiResponseReligion;
import co.id.cakap.network.ApiResponseUpdateProfile;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class MyProfileActivityPresenter implements MyProfileActivityContract.UserActionListener {
    private MyProfileActivityContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private ResultDataLogin mResultDataLogin;
    private Context mContext;

    public MyProfileActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(MyProfileActivityContract.View view, Context context){
        mView = view;
        mContext = context;
    }

    @Override
    public void getJenisKelamin() {
        mView.showProgressBar();

        mDataModel.deleteJenisKelaminData();
        mMainRepository.getJenisKelamin()
                .subscribe(new ResourceSubscriber<ApiResponseJenisKelamin>() {
                    @Override
                    public void onNext(ApiResponseJenisKelamin apiResponseJenisKelamin) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseJenisKelamin.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            saveJenisKelaminData(apiResponseJenisKelamin.getData());
                            getReligion();
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

                        mView.hideProgressBar();
                        mView.setErrorResponse(errorResponse);
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("onComplete");
                    }
                });
    }

    @Override
    public void getReligion() {
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
                            getProfileData();
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

                        mView.hideProgressBar();
                        mView.setErrorResponse(errorResponse);
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("onComplete");
                    }
                });
    }

    @Override
    public void getBank() {
        mView.showProgressBar();

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

                        mView.hideProgressBar();
                        mView.setErrorResponse(errorResponse);
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("onComplete");
                    }
                });
    }

    @Override
    public void getProfileData() {
        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.getProfileData(mResultDataLogin.getMember_id(), Utils.getGroupId(mContext))
                .subscribe(new ResourceSubscriber<ApiResponseProfileData>() {
                    @Override
                    public void onNext(ApiResponseProfileData apiResponseProfileData) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseProfileData.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            mView.setProfileData(apiResponseProfileData.getData());
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

                        mView.hideProgressBar();
                        mView.setErrorResponse(errorResponse);
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("onComplete");
                    }
                });
    }

    @Override
    public void checkData(String pob, String dob, String email, String hp, String telp, String kodePos,
                          String namaPewaris, String hubungan, String cabang, String norek,
                          boolean isFilledBank, boolean isFilledDob, boolean isFilledPostalCode) {
        List<ReligionData> religionData = mDataModel.getAllReligionData();
        boolean isCheckPob = false;
        boolean isCheckDob = false;
        boolean isCheckEmail = false;
        boolean isCheckHp = false;
        boolean isCheckTelp = false;
        boolean isCheckKodePos = false;
        boolean isCheckNamaPewaris = false;
        boolean isCheckHubungan = false;
        boolean isCheckCabang = false;
        boolean isCheckNorek = false;

        if (pob.length() == 0) {
            mView.setErrorPob(true);
        } else {
            mView.setErrorPob(false);
            isCheckPob = true;
        }

        if (isFilledDob) {
            isCheckDob = true;
        } else {
            if (dob.length() == 0) {
                mView.setErrorDob(true);
            } else {
                mView.setErrorDob(false);
                isCheckDob = true;
            }
        }

        if (email.length() == 0) {
            mView.setErrorEmail(true, false);
        } else {
            if (!Utils.isEmailValid(email)) {
                mView.setErrorEmail(true, true);
            } else {
                mView.setErrorEmail(false, true);
                isCheckEmail = true;
            }
        }

        if (hp.length() == 0) {
            mView.setErrorHp(true);
        } else {
            mView.setErrorHp(false);
            isCheckHp = true;
        }

        if (telp.length() == 0) {
            mView.setErrorTelp(true);
        } else {
            mView.setErrorTelp(false);
            isCheckTelp = true;
        }

        if (isFilledPostalCode) {
            isCheckKodePos = true;
        } else {
            if (kodePos.length() == 0) {
                mView.setErrorKodePos(true, false);
            } else {
                if (kodePos.length() < 5) {
                    mView.setErrorKodePos(true, true);
                } else {
                    mView.setErrorKodePos(false, true);
                    isCheckKodePos = true;
                }
            }
        }

        if (namaPewaris.length() == 0) {
            mView.setErrorNamaPewaris(true);
        } else {
            mView.setErrorNamaPewaris(false);
            isCheckNamaPewaris = true;
        }

        if (hubungan.length() == 0) {
            mView.setErrorHubungan(true);
        } else {
            mView.setErrorHubungan(false);
            isCheckHubungan = true;
        }

        if (isFilledBank) {
            isCheckCabang = true;
            isCheckNorek = true;
        } else {
            if (cabang.length() == 0) {
                mView.setErrorCabang(true);
            } else {
                mView.setErrorCabang(false);
                isCheckCabang = true;
            }

            if (norek.length() == 0) {
                mView.setErrorNorek(true);
            } else {
                mView.setErrorNorek(false);
                isCheckNorek = true;
            }
        }

        if (isCheckPob && isCheckDob && isCheckEmail && isCheckHp && isCheckTelp && isCheckKodePos &&
                isCheckNamaPewaris && isCheckHubungan && isCheckCabang && isCheckNorek) {
            mView.openDialogCheck();
        } else {
            mView.setErrorResponse("Some field are required!");
        }
    }

    @Override
    public void sendProfileData(String noKtp, String alamat, String kodePos, String npwp, String statusPernikahan,
                                String suami, String religion, String anak, String pekerjaan, String hubungan, String ahliWaris,
                                String city, String email, String pob, String gender, String date, String hp, String telp, String fax,
                                String kotaId, String province, String bankAcc, String bankId, String norek, String cabang, String area,
                                String nama, String pin) {
        mView.showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postUpdateProfile(noKtp, alamat, kodePos, npwp, statusPernikahan, suami, religion, anak, pekerjaan,
                hubungan, ahliWaris, city, email, pob, gender, date, hp, telp, fax, kotaId, province, bankAcc, bankId, norek, cabang,
                area, mResultDataLogin.getUsername(), nama, pin)
                .subscribe(new ResourceSubscriber<ApiResponseUpdateProfile>() {
                    @Override
                    public void onNext(ApiResponseUpdateProfile apiResponseUpdateProfile) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseUpdateProfile.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            getProfileData();
                            mView.openSuccessBottomSheet();
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

                        mView.hideProgressBar();
                        mView.setErrorResponse(errorResponse);
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("onComplete");
                    }
                });
    }

    private void saveJenisKelaminData(List<JenisKelaminData> jenisKelaminDataList) {
        for (int i = 0; i < jenisKelaminDataList.size(); i++) {
            mDataModel.insertJenisKelaminData(jenisKelaminDataList.get(i));
        }

        mView.setJenisKelaminData(jenisKelaminDataList);
    }

    private void saveReligionData(List<ReligionData> religionDataList) {
        ArrayList<String> religion = new ArrayList<>();

        for (int i = 0; i < religionDataList.size(); i++) {
            mDataModel.insertReligionData(religionDataList.get(i));
            religion.add(religionDataList.get(i).getAgama());
        }

        mView.setReligionData(religion);
    }

    private void saveBankData(List<BankData> bankDataList) {
        ArrayList<String> bankName = new ArrayList<>();
        ArrayList<String> bankId = new ArrayList<>();

        for (int i = 0; i < bankDataList.size(); i++) {
            mDataModel.insertBankData(bankDataList.get(i));
            bankId.add(bankDataList.get(i).getId());
            bankName.add(bankDataList.get(i).getName());
        }

        mView.setBankData(bankName, bankId);
    }
}
