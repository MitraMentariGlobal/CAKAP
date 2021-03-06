package co.id.cakap.ui.dashboard.restock;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.home.HomeContract;

public class RestockPresenter implements RestockContract.UserActionListener {
    private RestockContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public RestockPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(RestockContract.View view){
        mView = view;
    }
}
