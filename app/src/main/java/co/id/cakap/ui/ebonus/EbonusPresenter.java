package co.id.cakap.ui.ebonus;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.EbonusData;
import co.id.cakap.data.ItemEbonusCard;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;
import co.id.cakap.ui.stockReport.stockCard.StockCardContract;

public class EbonusPresenter implements EbonusContract.UserActionListener {
    private static WeakReference<EbonusContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private ArrayList<EbonusData> arrayList;
    private ArrayList<ItemEbonusCard> itemArrayList;

    public EbonusPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public EbonusPresenter() {

    }

    public void setView(EbonusContract.View view){
        mView = new WeakReference<>(view);
    }

    public EbonusContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new EbonusData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new EbonusData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new EbonusData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new EbonusData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new EbonusData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new EbonusData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new EbonusData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new EbonusData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new EbonusData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new EbonusData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        arrayList.add(new EbonusData("2019-11-14", "Blesstea Botol", "1 - Adjust Stock BC-MB", "1", "0", "80", "COMP911"));
        getView().setAdapter(arrayList);
    }

    @Override
    public void getItemProduct(String param) {
        itemArrayList = new ArrayList<>();
        itemArrayList.add(new ItemEbonusCard("BT01", "Blesstea Botol", "IDR 100.000.000"));
        itemArrayList.add(new ItemEbonusCard("BT02", "Blesstea Botol", "IDR 100.000.000"));
        itemArrayList.add(new ItemEbonusCard("BT03", "Blesstea Botol", "IDR 100.000.000"));
        itemArrayList.add(new ItemEbonusCard("BT04", "Blesstea Botol", "IDR 100.000.000"));
        itemArrayList.add(new ItemEbonusCard("BT05", "Blesstea Botol", "IDR 100.000.000"));
        itemArrayList.add(new ItemEbonusCard("BT06", "Blesstea Botol", "IDR 100.000.000"));
        itemArrayList.add(new ItemEbonusCard("BT07", "Blesstea Botol", "IDR 100.000.000"));
        itemArrayList.add(new ItemEbonusCard("BT08", "Blesstea Botol", "IDR 100.000.000"));
        itemArrayList.add(new ItemEbonusCard("BT09", "Blesstea Botol", "IDR 100.000.000"));
        itemArrayList.add(new ItemEbonusCard("BT10", "Blesstea Botol", "IDR 100.000.000"));
        getView().openDialogSearchData(itemArrayList);
    }
}
