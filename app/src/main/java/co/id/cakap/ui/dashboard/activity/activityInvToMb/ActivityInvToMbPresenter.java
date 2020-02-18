package co.id.cakap.ui.dashboard.activity.activityInvToMb;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.ActivityInvToMbData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseActivityCashbill;
import co.id.cakap.network.ApiResponseInvoiceToMb;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillContract;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ActivityInvToMbPresenter implements ActivityInvToMbContract.UserActionListener {
    private static WeakReference<ActivityInvToMbContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private static ResultDataLogin mResultDataLogin;

    private ArrayList<ActivityInvToMbData> arrayList;

    public ActivityInvToMbPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public ActivityInvToMbPresenter() {

    }

    public void setView(ActivityInvToMbContract.View view){
        mView = new WeakReference<>(view);
    }

    public ActivityInvToMbContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData(Context context, String tahun, String bulan) {
//        arrayList = new ArrayList<>();
//        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
//        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
//        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
//        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
//        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
//        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
//        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
//        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
//        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
//        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
//        getView().setAdapter(arrayList);

        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postInvoiceToMb(mResultDataLogin.getMember_id(), tahun, DateHelper.getMonthNumber(bulan), Utils.getGroupId(context))
                .subscribe(new ResourceSubscriber<ApiResponseInvoiceToMb>() {
                    @Override
                    public void onNext(ApiResponseInvoiceToMb apiResponseInvoiceToMb) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseInvoiceToMb.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            if (apiResponseInvoiceToMb.getData().isEmpty()) {
                                getView().hideProgressBar();
                                getView().setErrorResponse(apiResponseInvoiceToMb.getMessages());
                            } else {
                                getView().setAdapter(apiResponseInvoiceToMb.getData());
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
}
