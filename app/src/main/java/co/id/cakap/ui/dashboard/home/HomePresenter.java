package co.id.cakap.ui.dashboard.home;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import io.reactivex.subscribers.ResourceSubscriber;

public class HomePresenter implements HomeContract.UserActionListener {
    private HomeContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public HomePresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(HomeContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
        mView.setAdapter();
    }

    @Override
    public void saveData() {

    }
}
