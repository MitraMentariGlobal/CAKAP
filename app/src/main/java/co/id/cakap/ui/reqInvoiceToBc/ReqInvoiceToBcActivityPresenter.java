package co.id.cakap.ui.reqInvoiceToBc;

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
import co.id.cakap.network.ApiResponseItemInvoiceToBc;
import co.id.cakap.network.ApiResponseItemInvoiceToMb;
import co.id.cakap.network.ApiResponseSubmitInvoiceToBc;
import co.id.cakap.network.ApiResponseSubmitInvoiceToMb;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ReqInvoiceToBcActivityPresenter implements ReqInvoiceToBcActivityContract.UserActionListener {
    private static WeakReference<ReqInvoiceToBcActivityContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private static ResultDataLogin mResultDataLogin;

    private ArrayList<ItemShopCompanyData> arrayList;

    public ReqInvoiceToBcActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public ReqInvoiceToBcActivityPresenter() {

    }

    @Override
    public void setView(ReqInvoiceToBcActivityContract.View view) {
        mView = new WeakReference<>(view);
    }

    public ReqInvoiceToBcActivityContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData() {
//        arrayList = new ArrayList<>();
//        arrayList.add(new ItemShopCompanyData("BT01", "Blesstea Botol", "0", "115.000", "20", "0", "0"));
//        arrayList.add(new ItemShopCompanyData("BT02", "Blesstea Sachet", "0", "95.000", "20", "0", "0"));
//        arrayList.add(new ItemShopCompanyData("BT04", "Blesstea Pouch", "0", "80.000", "20", "0", "0"));
//        arrayList.add(new ItemShopCompanyData("PC05", "Blesstea Teessiu Sachet", "0", "72.000", "20", "0", "0"));
//        arrayList.add(new ItemShopCompanyData("PC06", "Blesstea Bellesha Body Shower Pink with Camellia", "3", "47.000", "20", "0", "0"));
//        arrayList.add(new ItemShopCompanyData("PC07", "Blesstea Bellesha Body Shower Camellia", "66", "47.000", "20", "0", "0"));
//        arrayList.add(new ItemShopCompanyData("PC08", "Blesstea Bellesha Shampoo", "120", "60.000", "20", "0", "0"));
//        arrayList.add(new ItemShopCompanyData("PC09", "V-Bless Pantyliner", "0", "37.000", "20", "0", "0"));
//        arrayList.add(new ItemShopCompanyData("PC10", "V-Bless Day", "0", "40.000", "20", "0", "0"));
//        arrayList.add(new ItemShopCompanyData("PC11", "V-Bless Nite", "0", "41.000", "20", "0", "0"));
//        getView().setAdapter(arrayList);
    }

    @Override
    public void getItemInvoice() {
        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postItemInvoiceToBc(mResultDataLogin.getLeader_ids(), mResultDataLogin.getMember_id())
                .subscribe(new ResourceSubscriber<ApiResponseItemInvoiceToBc>() {
                    @Override
                    public void onNext(ApiResponseItemInvoiceToBc apiResponseItemInvoiceToBc) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseItemInvoiceToBc.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            Constant.URL_IMAGE_PRODUCT = apiResponseItemInvoiceToBc.getUrl();
                            getView().setAdapter(apiResponseItemInvoiceToBc.getData());
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

        mMainRepository.postSubmitInvoiceToBc(getParam(pin, totalHarga, totalPv, totalBv, remark, resultData))
                .subscribe(new ResourceSubscriber<ApiResponseSubmitInvoiceToBc>() {
                    @Override
                    public void onNext(ApiResponseSubmitInvoiceToBc apiResponseSubmitInvoiceToBc) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseSubmitInvoiceToBc.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            saveData(apiResponseSubmitInvoiceToBc);
                            getView().successSubmitData(apiResponseSubmitInvoiceToBc);
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

//        Logger.d(getParam(pin, totalHarga, totalPv, totalBv, remark, resultData).toString());
    }

    private Map<String, Object> getParam(String pin, String totalHarga, String totalPv, String totalBv, String remark, List<ItemShopData> resultData) {
        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);

        List<Object> mAddCashbillDataList = new ArrayList<>();
        Map<String, Object> mParam = new HashMap<>();
        for (ItemShopData itemShopData : resultData) {
            Logger.d("item id : " + itemShopData.getItem_code());
            Logger.d("item name : " + itemShopData.getItem_name());
            Logger.d("cart : " + itemShopData.getCart());

            if (itemShopData.getCart() != null) {
                if (Integer.parseInt(itemShopData.getCart()) != 0) {
                    Map<String, Object> mDetail = new HashMap<>();
                    String harga = itemShopData.getHarga();
                    if (harga.contains(",")) {
                        harga = harga.split(",")[0];
                    } else if (harga.contains(".")) {
                        harga = harga.split("\\.")[0];
                    }

                    mDetail.put(Constant.BODY_TITIPAN_ID, itemShopData.getId());
                    mDetail.put(Constant.BODY_ITEM_ID, itemShopData.getItem_code());
                    mDetail.put(Constant.BODY_PRICE, harga);
                    mDetail.put(Constant.BODY_PV, itemShopData.getPv());
                    mDetail.put(Constant.BODY_BV, itemShopData.getBv());
                    mDetail.put(Constant.BODY_QTY, itemShopData.getCart());

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
        mParam.put(Constant.BODY_WILAYAH, mResultDataLogin.getWilayah());
        mParam.put(Constant.BODY_USER_NAME, mResultDataLogin.getUsername());
        mParam.put(Constant.BODY_BC_ID, mResultDataLogin.getLeader_ids());
        mParam.put(Constant.BODY_TGL, DateHelper.getTimeNowBackEnd());
        mParam.put(Constant.BODY_TOTAL_HARGA, totalHarga);
        mParam.put(Constant.BODY_TOTAL_PV, totalPv);
//        mParam.put(Constant.BODY_TOTAL_BV, totalBv);
        mParam.put(Constant.BODY_REMARK, remark);
        mParam.put(Constant.BODY_DETAIL, mAddCashbillDataList);

        return mParam;
    }

    public void saveData(ApiResponseSubmitInvoiceToBc apiResponseSubmitInvoiceToBc) {
        mDataModel.deleteReqInvoiceToBcSuccessData();
        for (int i = 0; i < apiResponseSubmitInvoiceToBc.getData().getDetail().size(); i++) {
            mDataModel.insertReqInvoiceToBcSuccessData(apiResponseSubmitInvoiceToBc.getData().getDetail().get(i));
        }
    }
}
