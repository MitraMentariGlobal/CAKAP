package co.id.cakap.ui.dashboard.activity.activityInvToMb;

import java.util.List;

import co.id.cakap.adapter.ActivityInvToMbAdapter;
import co.id.cakap.data.ActivityInvToMbData;

public class ActivityInvToMbContract {
    public interface View {
        void initializeData();
        void setAdapter(List<ActivityInvToMbData> resultData);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void openDetailTransaction(String transactionId);
    }

    public interface UserActionListener {
        void getData();
    }
}
