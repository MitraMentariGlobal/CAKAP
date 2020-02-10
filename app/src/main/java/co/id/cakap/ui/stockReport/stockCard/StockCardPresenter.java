package co.id.cakap.ui.stockReport.stockCard;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.ItemStockCard;
import co.id.cakap.data.RestockReqInvoiceData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.data.StockCardData;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseStockReportCard;
import co.id.cakap.network.ApiResponseStockReportCardItem;
import co.id.cakap.network.ApiResponseStockReportUpdate;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.restock.restockReqInvoice.RestockReqInvoiceContract;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class StockCardPresenter implements StockCardContract.UserActionListener {
    private static WeakReference<StockCardContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private static ResultDataLogin mResultDataLogin;
    private ArrayList<StockCardData> arrayList;
    private ArrayList<ItemStockCard> itemArrayList;

    public StockCardPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public StockCardPresenter() {

    }

    public void setView(StockCardContract.View view){
        mView = new WeakReference<>(view);
    }

    public StockCardContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData(String tahun, String bulan, String itemId, String price) {
//        arrayList = new ArrayList<>();
//        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
//        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
//        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
//        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
//        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
//        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
//        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
//        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
//        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
//        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
//        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
//        getView().setAdapter(arrayList);
        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postStockReportCard(mResultDataLogin.getMember_id(), tahun, DateHelper.getMonthNumber(bulan), itemId, price)
                .subscribe(new ResourceSubscriber<ApiResponseStockReportCard>() {
                    @Override
                    public void onNext(ApiResponseStockReportCard apiResponseStockReportCard) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseStockReportCard.getMessages());
                        Logger.d("<<<<<=====");

                        getView().setAdapter(apiResponseStockReportCard.getData());
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
    public void getItemProduct(String param) {
        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postStockReportCardItem(mResultDataLogin.getMember_id())
                .subscribe(new ResourceSubscriber<ApiResponseStockReportCardItem>() {
                    @Override
                    public void onNext(ApiResponseStockReportCardItem apiResponseStockReportCardItem) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseStockReportCardItem.getMessages());
                        Logger.d("<<<<<=====");

                        getView().openDialogSearchData(apiResponseStockReportCardItem.getData());
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
