package co.id.cakap.ui.cashbill;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.id.cakap.data.AddCashbillData;
import co.id.cakap.data.ItemShopData;
import co.id.cakap.data.OperationUserStatusData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.helper.Constant;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseItemCashbill;
import co.id.cakap.network.ApiResponseSearchMember;
import co.id.cakap.network.ApiResponseSearchMemberCashbill;
import co.id.cakap.network.ApiResponseSubmitCashbill;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.account.AccountContract;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class CashbillActivityPresenter implements CashbillActivityContract.UserActionListener {
    private static WeakReference<CashbillActivityContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private static ResultDataLogin mResultDataLogin;
    private static String mMemberId = "";

    public CashbillActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public CashbillActivityPresenter() {

    }

    @Override
    public void setView(CashbillActivityContract.View view) {
        mView = new WeakReference<>(view);
    }

    public CashbillActivityContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getMemberData(String memberId) {
        getView().showProgressBar();

        mMainRepository.postSearchMemberCashbill(memberId)
                .subscribe(new ResourceSubscriber<ApiResponseSearchMemberCashbill>() {
                    @Override
                    public void onNext(ApiResponseSearchMemberCashbill apiResponseSearchMemberCashbill) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseSearchMemberCashbill.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            mMemberId = memberId;
                            getItemCashbill(apiResponseSearchMemberCashbill.getData());
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
    public void getItemCashbill(OperationUserStatusData operationUserStatusData) {
        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postItemCashbill(mResultDataLogin.getMember_id())
                .subscribe(new ResourceSubscriber<ApiResponseItemCashbill>() {
                    @Override
                    public void onNext(ApiResponseItemCashbill apiResponseItemCashbill) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseItemCashbill.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            Constant.URL_IMAGE_PRODUCT = apiResponseItemCashbill.getUrl();
                            getView().setAdapter(apiResponseItemCashbill.getData(), operationUserStatusData);
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
    public void submitData(String pin, String totalHarga, String totalPv, String totalBv, String remark, List<ItemShopData> resultData) {
        getView().showProgressBar();

        mMainRepository.postSubmitCashbill(getParam(pin, totalHarga, totalPv, totalBv, remark, resultData))
                .subscribe(new ResourceSubscriber<ApiResponseSubmitCashbill>() {
                    @Override
                    public void onNext(ApiResponseSubmitCashbill apiResponseSubmitCashbill) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseSubmitCashbill.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            getView().successSubmitData(apiResponseSubmitCashbill.getData());
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

//        getParam(pin, totalHarga, totalPv, totalBv, remark, resultData);
    }

    private Map<String, Object> getParam(String pin, String totalHarga, String totalPv, String totalBv, String remark, List<ItemShopData> resultData) {
        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);

        List<Object> mAddCashbillDataList = new ArrayList<>();
        Map<String, Object> mParam = new HashMap<>();
        for (ItemShopData itemShopData : resultData) {
//        for (int i = 0; i < resultData.size(); i++) {
            if (itemShopData.getCart() != null) {
                if (Integer.parseInt(itemShopData.getCart()) != 0) {

                    Map<String, Object> mDetail = new HashMap<>();
                    Logger.d("input code : " + itemShopData.getItem_code());
                    Logger.d("input harga : " + itemShopData.getHarga());
                    Logger.d("input pv : " + itemShopData.getPv());
                    Logger.d("input bv : " + itemShopData.getBv());
                    Logger.d("input cart : " + itemShopData.getCart());
                    Logger.d("==============================================");

                    mDetail.put(Constant.BODY_TITIPAN_ID, itemShopData.getId());
                    mDetail.put(Constant.BODY_ITEM_ID, itemShopData.getItem_code());
                    mDetail.put(Constant.BODY_PRICE, itemShopData.getHarga());
                    mDetail.put(Constant.BODY_PV, itemShopData.getPv());
                    mDetail.put(Constant.BODY_BV, itemShopData.getBv());
                    mDetail.put(Constant.BODY_QTY, itemShopData.getCart());

                    Logger.d("detail : " + mDetail);
                    mAddCashbillDataList.add(mDetail);
                    Logger.d("**********************************************");
                }
            }
        }

        Logger.d("detail list : " + mAddCashbillDataList);

        mParam.put(Constant.BODY_PIN, pin);
        mParam.put(Constant.BODY_USER_ID, mResultDataLogin.getMember_id());
        mParam.put(Constant.BODY_WILAYAH, mResultDataLogin.getWilayah());
        mParam.put(Constant.BODY_USER_NAME, mResultDataLogin.getUsername());
        mParam.put(Constant.BODY_MEMBER_ID2, mMemberId);
        mParam.put(Constant.BODY_TGL, DateHelper.getTimeNowBackEnd());
        mParam.put(Constant.BODY_TOTAL_HARGA, totalHarga);
        mParam.put(Constant.BODY_TOTAL_PV, totalPv);
        mParam.put(Constant.BODY_TOTAL_BV, totalBv);
        mParam.put(Constant.BODY_REMARK, remark);
        mParam.put(Constant.BODY_DETAIL, mAddCashbillDataList);

        Logger.d("param : " + mParam);

        return mParam;
    }
}
