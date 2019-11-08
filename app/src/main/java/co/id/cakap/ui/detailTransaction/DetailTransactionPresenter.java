package co.id.cakap.ui.detailTransaction;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;

public class DetailTransactionPresenter implements DetailTransactionContract.UserActionListener {
    private static DetailTransactionContract.View mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    public DetailTransactionPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(DetailTransactionContract.View view){
        mView = view;
    }

    @Override
    public void getData() {

    }
}
