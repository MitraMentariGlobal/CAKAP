package co.id.cakap.ui.invoiceToMb;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.id.cakap.data.ItemShopData;
import co.id.cakap.data.OperationUserStatusData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.helper.Constant;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseItemInvoiceToMb;
import co.id.cakap.network.ApiResponseSearchMbInvoice;
import co.id.cakap.network.ApiResponseSearchMemberCashbill;
import co.id.cakap.network.ApiResponseSubmitCashbill;
import co.id.cakap.network.ApiResponseSubmitInvoiceToMb;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class InvoiceToMbActivityPresenter implements InvoiceToMbActivityContract.UserActionListener {
    private static WeakReference<InvoiceToMbActivityContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private static ResultDataLogin mResultDataLogin;
    private static String mMbId = "";

    private OperationUserStatusData operationUserStatusData;
    private ArrayList<ItemShopData> arrayList;

    public InvoiceToMbActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public InvoiceToMbActivityPresenter() {

    }

    @Override
    public void setView(InvoiceToMbActivityContract.View view) {
        mView = new WeakReference<>(view);
    }

    public InvoiceToMbActivityContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData(String memberId) {
        operationUserStatusData = new OperationUserStatusData(memberId, "Nama MB 1", "Active");

        arrayList = new ArrayList<>();
//        arrayList.add(new ItemShopData("BT01", "Blesstea Botol", "0", "35", "Wilayah I", "115.000", "20", "0", "0"));
//        arrayList.add(new ItemShopData("BT02", "Blesstea Sachet", "0", "46", "Wilayah I", "95.000", "20", "0", "0"));
//        arrayList.add(new ItemShopData("BT04", "Blesstea Pouch", "0", "100", "Wilayah I", "80.000", "20", "0", "0"));
//        arrayList.add(new ItemShopData("PC05", "Blesstea Teessiu Sachet", "0", "33", "Wilayah I", "72.000", "20", "0", "0"));
//        arrayList.add(new ItemShopData("PC06", "Blesstea Bellesha Body Shower Pink with Camellia", "3", "100", "Wilayah I", "47.000", "20", "0", "0"));
//        arrayList.add(new ItemShopData("PC07", "Blesstea Bellesha Body Shower Camellia", "66", "100", "Wilayah I", "47.000", "20", "0", "0"));
//        arrayList.add(new ItemShopData("PC08", "Blesstea Bellesha Shampoo", "120", "100", "Wilayah I", "60.000", "20", "0", "0"));
//        arrayList.add(new ItemShopData("PC09", "V-Bless Pantyliner", "0", "87", "Wilayah I", "37.000", "20", "0", "0"));
//        arrayList.add(new ItemShopData("PC10", "V-Bless Day", "0", "9", "Wilayah I", "40.000", "20", "0", "0"));
//        arrayList.add(new ItemShopData("PC11", "V-Bless Nite", "0", "7", "Wilayah I", "41.000", "20", "0", "0"));
        getView().setAdapter(arrayList, operationUserStatusData);
    }

    @Override
    public void getMbData(String mbId) {
        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postSearchMbInvoice(mResultDataLogin.getMember_id(), mbId)
                .subscribe(new ResourceSubscriber<ApiResponseSearchMbInvoice>() {
                    @Override
                    public void onNext(ApiResponseSearchMbInvoice apiResponseSearchMbInvoice) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseSearchMbInvoice.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            getItemInvoice(apiResponseSearchMbInvoice.getData());
                            mMbId = apiResponseSearchMbInvoice.getData().getUser_code();
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
    public void getItemInvoice(OperationUserStatusData operationUserStatusData) {
        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postItemInvoiceToMb(mResultDataLogin.getMember_id(), operationUserStatusData.getUser_code())
                .subscribe(new ResourceSubscriber<ApiResponseItemInvoiceToMb>() {
                    @Override
                    public void onNext(ApiResponseItemInvoiceToMb apiResponseItemInvoiceToMb) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseItemInvoiceToMb.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            getView().setAdapter(apiResponseItemInvoiceToMb.getData(), operationUserStatusData);
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

        mMainRepository.postSubmitInvoiceToMb(getParam(pin, totalHarga, totalPv, totalBv, remark, resultData))
                .subscribe(new ResourceSubscriber<ApiResponseSubmitInvoiceToMb>() {
                    @Override
                    public void onNext(ApiResponseSubmitInvoiceToMb apiResponseSubmitInvoiceToMb) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseSubmitInvoiceToMb.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            saveData(apiResponseSubmitInvoiceToMb);
                            getView().successSubmitData(apiResponseSubmitInvoiceToMb.getData());
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

    private Map<String, Object> getParam(String pin, String totalHarga, String totalPv, String totalBv, String remark, List<ItemShopData> resultData) {
        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);

        List<Object> mAddCashbillDataList = new ArrayList<>();
        Map<String, Object> mParam = new HashMap<>();
        for (ItemShopData itemShopData : resultData) {
            if (itemShopData.getCart() != null) {
                if (Integer.parseInt(itemShopData.getCart()) != 0) {
                    Map<String, Object> mDetail = new HashMap<>();

                    mDetail.put(Constant.BODY_TITIPAN_ID, itemShopData.getId());
                    mDetail.put(Constant.BODY_ITEM_ID, itemShopData.getItem_code());
                    mDetail.put(Constant.BODY_PRICE, itemShopData.getHarga());
                    mDetail.put(Constant.BODY_PV, itemShopData.getPv());
//                    mDetail.put(Constant.BODY_BV, itemShopData.getBv());
                    mDetail.put(Constant.BODY_QTY, itemShopData.getCart());

                    mAddCashbillDataList.add(mDetail);
                }
            }
        }

        mParam.put(Constant.BODY_PIN, pin);
        mParam.put(Constant.BODY_USER_ID, mResultDataLogin.getMember_id());
        mParam.put(Constant.BODY_WILAYAH, mResultDataLogin.getWilayah());
        mParam.put(Constant.BODY_USER_NAME, mResultDataLogin.getUsername());
        mParam.put(Constant.BODY_MEMBER_ID2, mMbId);
        mParam.put(Constant.BODY_TGL, DateHelper.getTimeNowBackEnd());
        mParam.put(Constant.BODY_TOTAL_HARGA, totalHarga);
        mParam.put(Constant.BODY_TOTAL_PV, totalPv);
//        mParam.put(Constant.BODY_TOTAL_BV, totalBv);
        mParam.put(Constant.BODY_REMARK, remark);
        mParam.put(Constant.BODY_DETAIL, mAddCashbillDataList);

        return mParam;
    }

    public void saveData(ApiResponseSubmitInvoiceToMb apiResponseSubmitInvoiceToMb) {
        mDataModel.deleteInvoiceToMbSuccessDetailData();
        for (int i = 0; i < apiResponseSubmitInvoiceToMb.getData().getDetail().size(); i++) {
            mDataModel.insertInvoiceToMbSuccessDetailData(apiResponseSubmitInvoiceToMb.getData().getDetail().get(i));
        }
    }
}
