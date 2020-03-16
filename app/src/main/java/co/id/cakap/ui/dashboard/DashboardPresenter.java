package co.id.cakap.ui.dashboard;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.lang.ref.WeakReference;

import co.id.cakap.BuildConfig;
import co.id.cakap.helper.Constant;
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

    @Override
    public void checkVersionUpdate() {
        FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        firebaseRemoteConfig.fetch(120) // fetch every 2 minutes
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        firebaseRemoteConfig.activateFetched();

                        boolean isCancelDialog = firebaseRemoteConfig.getBoolean(Constant.FIREBASE_IS_CANCEL_DIALOG);
                        boolean isUpdate = firebaseRemoteConfig.getBoolean(Constant.FIREBASE_IS_UPDATE);
                        long updateVersion = firebaseRemoteConfig.getLong(Constant.FIREBASE_UPDATE_VERSION);
                        long appVersion = BuildConfig.VERSION_CODE;
                        String updateUrl = firebaseRemoteConfig.getString(Constant.FIREBASE_LINK_UPDATE_APP);

                        if (isUpdate) {
                            if (updateVersion > appVersion) {
                                getView().openDialogUpdate(updateUrl, isCancelDialog);
                            }
                        }
                    }
                });
    }
}
