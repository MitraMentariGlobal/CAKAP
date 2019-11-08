package co.id.cakap.ui.dashboard.activity.activityReqInvMb;

import java.util.List;

import co.id.cakap.adapter.ActivityReqInvMbAdapter;
import co.id.cakap.data.ActivityReqInvMbData;

public class ActivityReqInvMbContract {
    public interface View {
        void initializeData();
        void setAdapter(List<ActivityReqInvMbData> resultData);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void openDetailTransaction();
    }

    public interface UserActionListener {
        void getData();
    }
}
