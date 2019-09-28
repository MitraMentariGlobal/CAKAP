package co.id.cakap.ui.splash_screen;

import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseSession;
import co.id.cakap.repository.MainRepository;

public class SplashScreenPresenter implements SplashScreenContract.UserActionListener{
    private SplashScreenContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public SplashScreenPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(SplashScreenContract.View view) {
        mView = view;
    }
}
