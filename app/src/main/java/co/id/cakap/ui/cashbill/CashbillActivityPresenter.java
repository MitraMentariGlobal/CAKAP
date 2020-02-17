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

    private OperationUserStatusData operationUserStatusData;
    private ArrayList<ItemShopData> arrayList;

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
//        operationUserStatusData = new OperationUserStatusData(memberId, "Nama Member 1", "Active");
//
//        arrayList = new ArrayList<>();
//        arrayList.add(new ItemShopData("BT01", "Blesstea Botol", "0", "35", "Wilayah I", "115.000", "20", "0", "0"));
//        arrayList.add(new ItemShopData("BT02", "Blesstea Sachet", "0", "46", "Wilayah I", "95.000", "20", "0", "0"));
//        arrayList.add(new ItemShopData("BT04", "Blesstea Pouch", "0", "100", "Wilayah I", "80.000", "20", "0", "0"));
//        arrayList.add(new ItemShopData("PC05", "Blesstea Teessiu Sachet", "0", "33", "Wilayah I", "72.000", "20", "0", "0"));
//        arrayList.add(new ItemShopData("PC06", "Blesstea Bellesha Body Shower Pink with Camellia", "3", "100", "Wilayah I", "47.000", "20", "0", "0"));
//        arrayList.add(new ItemShopData("PC07", "Blesstea Bellesha Shampoo", "66", "100", "Wilayah I", "47.000", "20", "0", "0"));
//        arrayList.add(new ItemShopData("PC08", "Blesstea Bellesha Shampoo", "120", "100", "Wilayah I", "60.000", "20", "0", "0"));
//        arrayList.add(new ItemShopData("PC09", "V-Bless Pantyliner", "0", "87", "Wilayah I", "37.000", "20", "0", "0"));
//        arrayList.add(new ItemShopData("PC10", "V-Bless Day", "0", "9", "Wilayah I", "40.000", "20", "0", "0"));
//        arrayList.add(new ItemShopData("PC11", "V-Bless Nite", "0", "7", "Wilayah I", "41.000", "20", "0", "0"));
//        arrayList.add(new ItemShopData("PC11", "V-Bless Nite", "0", "7", "Wilayah I", "41.000", "20", "0", "0"));
//        getView().setAdapter(arrayList, operationUserStatusData);


        getView().showProgressBar();

        mMainRepository.postSearchMemberCashbill(memberId)
                .subscribe(new ResourceSubscriber<ApiResponseSearchMemberCashbill>() {
                    @Override
                    public void onNext(ApiResponseSearchMemberCashbill apiResponseSearchMemberCashbill) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseSearchMemberCashbill.getMessages());
                        Logger.d("<<<<<=====");

                        mMemberId = memberId;
                        getItemCashbill(apiResponseSearchMemberCashbill.getData());
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

                        Constant.URL_IMAGE_PRODUCT = apiResponseItemCashbill.getUrl();
                        getView().setAdapter(apiResponseItemCashbill.getData(), operationUserStatusData);
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

                        getView().successSubmitData(apiResponseSubmitCashbill.getData());
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

    private Map<String, Object> getParam(String pin, String totalHarga, String totalPv, String totalBv, String remark, List<ItemShopData> resultData) {

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);

        Map<String, Object> detail = new HashMap<>();
        Map<String, Object> param = new HashMap<>();

        List<Object> addCashbillDataList = new ArrayList<Object>();

        for (ItemShopData itemShopData : resultData) {
            if (itemShopData.getCart() != null) {
                if (Integer.parseInt(itemShopData.getCart()) != 0) {
                    detail.put(Constant.BODY_TITIPAN_ID, itemShopData.getId());
                    detail.put(Constant.BODY_ITEM_ID, itemShopData.getItem_code());
                    detail.put(Constant.BODY_PRICE, itemShopData.getHarga());
                    detail.put(Constant.BODY_PV, itemShopData.getPv());
                    detail.put(Constant.BODY_BV, itemShopData.getBv());
                    detail.put(Constant.BODY_QTY, itemShopData.getCart());

                    addCashbillDataList.add(detail);
                }
            }
        }

        param.put(Constant.BODY_PIN, pin);
        param.put(Constant.BODY_USER_ID, mResultDataLogin.getMember_id());
        param.put(Constant.BODY_WILAYAH, mResultDataLogin.getWilayah());
        param.put(Constant.BODY_USER_NAME, mResultDataLogin.getUsername());
        param.put(Constant.BODY_MEMBER_ID2, mMemberId);
        param.put(Constant.BODY_TGL, DateHelper.getTimeNowBackEnd());
        param.put(Constant.BODY_TOTAL_HARGA, totalHarga);
        param.put(Constant.BODY_TOTAL_PV, totalPv);
        param.put(Constant.BODY_TOTAL_BV, totalBv);
        param.put(Constant.BODY_REMARK, remark);
        param.put(Constant.BODY_DETAIL, addCashbillDataList);

        Logger.d("param : " + param);

        return param;
    }
}
