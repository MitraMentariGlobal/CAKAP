package co.id.cakap.ui.dashboard.activity.activityInvToMb;

import android.content.Context;

import java.util.List;

import co.id.cakap.adapter.ActivityInvToMbAdapter;
import co.id.cakap.data.ActivityCashbillData;
import co.id.cakap.data.ActivityInvToMbData;

public class ActivityInvToMbContract {
    public interface View {
        void initializeData();
        void setAdapter(List<ActivityInvToMbData> resultData);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void openDetailTransaction(ActivityInvToMbData activityInvToMbData);
        void createActivationForm(ActivityInvToMbData activityInvToMbData);
        void openPinDialog(ActivityInvToMbData activityInvToMbData);
    }

    public interface UserActionListener {
        void getData(Context context, String tahun, String bulan);
        void cancelTransaction(ActivityInvToMbData activityInvToMbData, String pin, Context context, String tahun, String bulan);
    }
}
