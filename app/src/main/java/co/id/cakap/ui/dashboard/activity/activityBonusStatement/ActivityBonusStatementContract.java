package co.id.cakap.ui.dashboard.activity.activityBonusStatement;

public class ActivityBonusStatementContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(ActivityBonusStatementContract.View view);
    }
}
