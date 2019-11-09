package co.id.cakap.ui.searchMember;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;

public class SearchMemberActivityPresenter implements SearchMemberActivityContract.UserActionListener {
    private SearchMemberActivityContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public SearchMemberActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(SearchMemberActivityContract.View view){
        mView = view;
    }
}
