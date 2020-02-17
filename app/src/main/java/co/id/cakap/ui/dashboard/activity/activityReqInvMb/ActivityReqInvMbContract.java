package co.id.cakap.ui.dashboard.activity.activityReqInvMb;

import android.content.Context;

import java.util.List;

import co.id.cakap.adapter.ActivityReqInvMbAdapter;
import co.id.cakap.data.ActivityCashbillData;
import co.id.cakap.data.ActivityReqInvMbData;

public class ActivityReqInvMbContract {
    public interface View {
        void initializeData();
        void setAdapter(List<ActivityReqInvMbData> resultData);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void openDetailTransaction(ActivityReqInvMbData activityReqInvMbData);
        void openPinDialog(ActivityReqInvMbData activityReqInvMbData, String action);
    }

    public interface UserActionListener {
        void getData(Context context);
        void actionTransaction(ActivityReqInvMbData activityReqInvMbData, String pin, Context context, String action);
    }
}
