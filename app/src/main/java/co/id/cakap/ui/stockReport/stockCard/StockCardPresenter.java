package co.id.cakap.ui.stockReport.stockCard;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.ItemStockCard;
import co.id.cakap.data.RestockReqInvoiceData;
import co.id.cakap.data.StockCardData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.restock.restockReqInvoice.RestockReqInvoiceContract;

public class StockCardPresenter implements StockCardContract.UserActionListener {
    private static WeakReference<StockCardContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

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
    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new StockCardData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        getView().setAdapter(arrayList);
    }

    @Override
    public void getItemProduct(String param) {
        itemArrayList = new ArrayList<>();
        itemArrayList.add(new ItemStockCard("BT01", "Blesstea Botol", "IDR 100.000.000"));
        itemArrayList.add(new ItemStockCard("BT02", "Blesstea Botol", "IDR 100.000.000"));
        itemArrayList.add(new ItemStockCard("BT03", "Blesstea Botol", "IDR 100.000.000"));
        itemArrayList.add(new ItemStockCard("BT04", "Blesstea Botol", "IDR 100.000.000"));
        itemArrayList.add(new ItemStockCard("BT05", "Blesstea Botol", "IDR 100.000.000"));
        itemArrayList.add(new ItemStockCard("BT06", "Blesstea Botol", "IDR 100.000.000"));
        itemArrayList.add(new ItemStockCard("BT07", "Blesstea Botol", "IDR 100.000.000"));
        itemArrayList.add(new ItemStockCard("BT08", "Blesstea Botol", "IDR 100.000.000"));
        itemArrayList.add(new ItemStockCard("BT09", "Blesstea Botol", "IDR 100.000.000"));
        itemArrayList.add(new ItemStockCard("BT10", "Blesstea Botol", "IDR 100.000.000"));
        getView().openDialogSearchData(itemArrayList);
    }
}
