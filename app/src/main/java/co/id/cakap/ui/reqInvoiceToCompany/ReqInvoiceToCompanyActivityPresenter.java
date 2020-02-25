package co.id.cakap.ui.reqInvoiceToCompany;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.id.cakap.data.ItemShopCompanyData;
import co.id.cakap.data.ItemShopData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.helper.Constant;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseAddEditAddress;
import co.id.cakap.network.ApiResponseItemInvoiceToCompany;
import co.id.cakap.network.ApiResponseKota;
import co.id.cakap.network.ApiResponseSubmitCashbill;
import co.id.cakap.network.ApiResponseSubmitInvoiceToCompany;
import co.id.cakap.network.ApiResponseSubmitInvoiceToMb;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ReqInvoiceToCompanyActivityPresenter implements ReqInvoiceToCompanyActivityContract.UserActionListener {
    private static WeakReference<ReqInvoiceToCompanyActivityContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private static ResultDataLogin mResultDataLogin;

    private ArrayList<ItemShopCompanyData> arrayList;

    public ReqInvoiceToCompanyActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public ReqInvoiceToCompanyActivityPresenter() {

    }

    @Override
    public void setView(ReqInvoiceToCompanyActivityContract.View view) {
        mView = new WeakReference<>(view);
    }

    public ReqInvoiceToCompanyActivityContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getItemInvoice(String timur) {
        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postItemInvoiceToCompany(timur)
                .subscribe(new ResourceSubscriber<ApiResponseItemInvoiceToCompany>() {
                    @Override
                    public void onNext(ApiResponseItemInvoiceToCompany apiResponseItemInvoiceToCompany) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseItemInvoiceToCompany.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            getView().setAdapter(apiResponseItemInvoiceToCompany.getData());
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
    public void submitData(String pin, String totalHarga, String totalPv, String totalBv, String remark, String opsi, String idAddress, List<ItemShopCompanyData> resultData) {
        getView().showProgressBar();

        mMainRepository.postSubmitInvoiceToCompany(getParam(pin, totalHarga, totalPv, totalBv, remark, opsi, idAddress, resultData))
                .subscribe(new ResourceSubscriber<ApiResponseSubmitInvoiceToCompany>() {
                    @Override
                    public void onNext(ApiResponseSubmitInvoiceToCompany apiResponseSubmitInvoiceToCompany) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseSubmitInvoiceToCompany.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            saveData(apiResponseSubmitInvoiceToCompany);
                            getView().successSubmitData(apiResponseSubmitInvoiceToCompany);
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

    private Map<String, Object> getParam(String pin, String totalHarga, String totalPv, String totalBv, String remark, String opsi, String idAddress, List<ItemShopCompanyData> resultData) {
        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);

        List<Object> mAddCashbillDataList = new ArrayList<>();
        Map<String, Object> mParam = new HashMap<>();
        for (ItemShopCompanyData itemShopCompanyData : resultData) {
            if (itemShopCompanyData.getCart() != null) {
                if (Integer.parseInt(itemShopCompanyData.getCart()) != 0) {
                    Map<String, Object> mDetail = new HashMap<>();
                    String harga = itemShopCompanyData.getHarga();
                    if (harga.contains(",")) {
                        harga = harga.split(",")[0];
                    } else if (harga.contains(".")) {
                        harga = harga.split("\\.")[0];
                    }

//                    mDetail.put(Constant.BODY_TITIPAN_ID, itemShopCompanyData.getId());
                    mDetail.put(Constant.BODY_ITEM_ID, itemShopCompanyData.getItem_code());
                    mDetail.put(Constant.BODY_PRICE, harga);
                    mDetail.put(Constant.BODY_PV, itemShopCompanyData.getPv());
                    mDetail.put(Constant.BODY_BV, itemShopCompanyData.getBv());
                    mDetail.put(Constant.BODY_QTY, itemShopCompanyData.getCart());

                    mAddCashbillDataList.add(mDetail);
                }
            }
        }

        if (totalHarga.contains(",")) {
            totalHarga = totalHarga.split(",")[0];
        } else if (totalHarga.contains(".")) {
            totalHarga = totalHarga.split("\\.")[0];
        }

        mParam.put(Constant.BODY_PIN, pin);
        mParam.put(Constant.BODY_USER_ID, mResultDataLogin.getMember_id());
//        mParam.put(Constant.BODY_WILAYAH, mResultDataLogin.getWilayah());
        mParam.put(Constant.BODY_USER_NAME, mResultDataLogin.getUsername());
//        mParam.put(Constant.BODY_MEMBER_ID2, mMemberId);
        mParam.put(Constant.BODY_TGL, DateHelper.getTimeNowBackEnd());
        mParam.put(Constant.BODY_TOTAL_HARGA, totalHarga);
        mParam.put(Constant.BODY_TOTAL_PV, totalPv);
//        mParam.put(Constant.BODY_TOTAL_BV, totalBv);
        mParam.put(Constant.BODY_OPSI, opsi);
        mParam.put(Constant.BODY_IDADDR, idAddress);
        mParam.put(Constant.BODY_REMARK, remark);
        mParam.put(Constant.BODY_DETAIL, mAddCashbillDataList);

        return mParam;
    }

    public void saveData(ApiResponseSubmitInvoiceToCompany apiResponseSubmitInvoiceToCompany) {
        mDataModel.deleteReqInvoiceToCompanySuccessData();
        for (int i = 0; i < apiResponseSubmitInvoiceToCompany.getData().getDetail().size(); i++) {
            mDataModel.insertReqInvoiceToCompanySuccessData(apiResponseSubmitInvoiceToCompany.getData().getDetail().get(i));
        }

        mDataModel.deleteBankInfoData();
        for (int i = 0; i < apiResponseSubmitInvoiceToCompany.getBank().size(); i++) {
            mDataModel.insertBankInfoData(apiResponseSubmitInvoiceToCompany.getBank().get(i));
        }
    }
}
