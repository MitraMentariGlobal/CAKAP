package co.id.cakap.ui.bonusStatement;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.downlineListing.DownlineListingContract;

public class BonusStatementPresenter implements BonusStatementContract.UserActionListener {
    private BonusStatementContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public BonusStatementPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(BonusStatementContract.View view){
        mView = view;
    }
}
