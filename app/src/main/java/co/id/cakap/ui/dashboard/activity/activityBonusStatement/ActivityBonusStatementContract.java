package co.id.cakap.ui.dashboard.activity.activityBonusStatement;

import android.content.Context;

import co.id.cakap.data.ActivityBonusStatementData;

public class ActivityBonusStatementContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void checkPermissionStorage(ActivityBonusStatementData activityBonusStatementData);
    }

    public interface UserActionListener{
        void setView(ActivityBonusStatementContract.View view);
        void getData(String tahun, String bulan);
    }
}
