package co.id.cakap.ui.main;

import co.id.cakap.data.ResultData;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponse;
import co.id.cakap.repository.MainRepository;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class MainPresenter implements MainContract.UserActionListener {
    private MainContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public MainPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(MainContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
        mDataModel.deleteDataList();
        mView.showProgressBar();
        mMainRepository.getData()
                .subscribe(new ResourceSubscriber<ApiResponse>() {
                    @Override
                    public void onNext(ApiResponse apiResponse) {
                        mView.hideProgressBar();
                        mView.setAdapter(apiResponse.getResults());
                        saveData(apiResponse);
                    }

                    @Override
                    public void onError(Throwable t) {
                        //Handle when onErrorResponse From API
                        mView.hideProgressBar();
                        mView.setAdapter(mDataModel.getAllData());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void saveData(ApiResponse apiResponse) {
        for(ResultData resultData : apiResponse.getResults()){
            mDataModel.insertData(resultData);
        }
    }
}
