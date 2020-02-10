package co.id.cakap.ui.dashboard.home;

import java.util.List;

import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import io.reactivex.subscribers.ResourceSubscriber;

public class HomePresenter implements HomeContract.UserActionListener {
    private HomeContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private ResultDataLogin mResultDataLogin;

    public HomePresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(HomeContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mView.setAdapter(mResultDataLogin);
    }
}
