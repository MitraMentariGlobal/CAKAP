package co.id.cakap.ui.dashboard.activity.activityCashbill;

import java.util.List;

import co.id.cakap.data.ActivityCashbillData;

public class ActivityCashbillContract {
    public interface View {
        void initializeData();
        void setAdapter(List<ActivityCashbillData> resultData);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void openDetailTransaction();
    }

    public interface UserActionListener {
        void getData();
    }
}
