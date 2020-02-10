package co.id.cakap.ui.dashboard;

import java.lang.ref.WeakReference;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;

public class DashboardPresenter implements DashboardContract.UserActionListener{
    private static WeakReference<DashboardContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    public DashboardPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public DashboardPresenter() {

    }

    @Override
    public void setView(DashboardContract.View view){
        mView = new WeakReference<>(view);
    }

    public DashboardContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }
}
