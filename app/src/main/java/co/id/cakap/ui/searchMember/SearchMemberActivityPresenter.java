package co.id.cakap.ui.searchMember;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.SearchMemberData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;

public class SearchMemberActivityPresenter implements SearchMemberActivityContract.UserActionListener {
    private static WeakReference<SearchMemberActivityContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<SearchMemberData> arrayList;

    public SearchMemberActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public SearchMemberActivityPresenter() {

    }

    @Override
    public void setView(SearchMemberActivityContract.View view) {
        mView = new WeakReference<>(view);
    }

    public SearchMemberActivityContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getDataMember(String param) {
        arrayList = new ArrayList<>();
        arrayList.add(new SearchMemberData("00000021", "RD", "Nama Member 1", "6473648566438824", "JAKARTA SELATAN", "DKI JAKARTA", "ACTIVE", "0000003", "LILY SUKHAMTA", "0000003", "LILY SUKHAMTA"));
        arrayList.add(new SearchMemberData("00000022", "RD", "Nama Member 1", "6473648566438824", "JAKARTA SELATAN", "DKI JAKARTA", "ACTIVE", "0000003", "LILY SUKHAMTA", "0000003", "LILY SUKHAMTA"));
        arrayList.add(new SearchMemberData("00000023", "RD", "Nama Member 1", "6473648566438824", "JAKARTA SELATAN", "DKI JAKARTA", "INACTIVE", "0000003", "LILY SUKHAMTA", "0000003", "LILY SUKHAMTA"));
        arrayList.add(new SearchMemberData("00000024", "RD", "Nama Member 1", "6473648566438824", "JAKARTA SELATAN", "DKI JAKARTA", "ACTIVE", "0000003", "LILY SUKHAMTA", "0000003", "LILY SUKHAMTA"));
        arrayList.add(new SearchMemberData("00000025", "RD", "Nama Member 1", "6473648566438824", "JAKARTA SELATAN", "DKI JAKARTA", "INACTIVE", "0000003", "LILY SUKHAMTA", "0000003", "LILY SUKHAMTA"));
        arrayList.add(new SearchMemberData("00000026", "RD", "Nama Member 1", "6473648566438824", "JAKARTA SELATAN", "DKI JAKARTA", "ACTIVE", "0000003", "LILY SUKHAMTA", "0000003", "LILY SUKHAMTA"));
        arrayList.add(new SearchMemberData("00000027", "RD", "Nama Member 1", "6473648566438824", "JAKARTA SELATAN", "DKI JAKARTA", "ACTIVE", "0000003", "LILY SUKHAMTA", "0000003", "LILY SUKHAMTA"));
        arrayList.add(new SearchMemberData("00000028", "RD", "Nama Member 1", "6473648566438824", "JAKARTA SELATAN", "DKI JAKARTA", "INACTIVE", "0000003", "LILY SUKHAMTA", "0000003", "LILY SUKHAMTA"));
        arrayList.add(new SearchMemberData("00000029", "RD", "Nama Member 1", "6473648566438824", "JAKARTA SELATAN", "DKI JAKARTA", "ACTIVE", "0000003", "LILY SUKHAMTA", "0000003", "LILY SUKHAMTA"));
        arrayList.add(new SearchMemberData("00000030", "RD", "Nama Member 1", "6473648566438824", "JAKARTA SELATAN", "DKI JAKARTA", "ACTIVE", "0000003", "LILY SUKHAMTA", "0000003", "LILY SUKHAMTA"));
        arrayList.add(new SearchMemberData("00000031", "RD", "Nama Member 1", "6473648566438824", "JAKARTA SELATAN", "DKI JAKARTA", "INACTIVE", "0000003", "LILY SUKHAMTA", "0000003", "LILY SUKHAMTA"));
        arrayList.add(new SearchMemberData("00000032", "RD", "Nama Member 1", "6473648566438824", "JAKARTA SELATAN", "DKI JAKARTA", "ACTIVE", "0000003", "LILY SUKHAMTA", "0000003", "LILY SUKHAMTA"));
        arrayList.add(new SearchMemberData("00000033", "RD", "Nama Member 1", "6473648566438824", "JAKARTA SELATAN", "DKI JAKARTA", "ACTIVE", "0000003", "LILY SUKHAMTA", "0000003", "LILY SUKHAMTA"));
        arrayList.add(new SearchMemberData("00000034", "RD", "Nama Member 1", "6473648566438824", "JAKARTA SELATAN", "DKI JAKARTA", "ACTIVE", "0000003", "LILY SUKHAMTA", "0000003", "LILY SUKHAMTA"));
        arrayList.add(new SearchMemberData("00000035", "RD", "Nama Member 1", "6473648566438824", "JAKARTA SELATAN", "DKI JAKARTA", "ACTIVE", "0000003", "LILY SUKHAMTA", "0000003", "LILY SUKHAMTA"));
        arrayList.add(new SearchMemberData("00000036", "RD", "Nama Member 1", "6473648566438824", "JAKARTA SELATAN", "DKI JAKARTA", "ACTIVE", "0000003", "LILY SUKHAMTA", "0000003", "LILY SUKHAMTA"));
        arrayList.add(new SearchMemberData("00000037", "RD", "Nama Member 1", "6473648566438824", "JAKARTA SELATAN", "DKI JAKARTA", "ACTIVE", "0000003", "LILY SUKHAMTA", "0000003", "LILY SUKHAMTA"));
        arrayList.add(new SearchMemberData("00000038", "RD", "Nama Member 1", "6473648566438824", "JAKARTA SELATAN", "DKI JAKARTA", "ACTIVE", "0000003", "LILY SUKHAMTA", "0000003", "LILY SUKHAMTA"));
        getView().setAdapter(arrayList);
    }
}
