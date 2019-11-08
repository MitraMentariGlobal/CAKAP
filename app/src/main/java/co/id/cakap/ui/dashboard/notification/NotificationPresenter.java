package co.id.cakap.ui.dashboard.notification;

import java.lang.ref.WeakReference;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;

public class NotificationPresenter implements NotificationContract.UserActionListener {
    private static WeakReference<NotificationContract.View> mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public NotificationPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(NotificationContract.View view){
        mView = new WeakReference<>(view);
    }

    public NotificationContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }
}
