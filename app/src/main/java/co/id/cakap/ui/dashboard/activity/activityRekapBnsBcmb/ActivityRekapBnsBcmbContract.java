package co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb;

import java.util.List;

import co.id.cakap.adapter.ActivityRekapBnsBcmbAdapter;
import co.id.cakap.data.ActivityRekapBnsBcmbData;

public class ActivityRekapBnsBcmbContract {
    public interface View {
        void initializeData();
        void setAdapter(List<ActivityRekapBnsBcmbData> resultData);
        void showProgressBar();
        void hideProgressBar();
    }

    public interface UserActionListener {
        void getData();
    }
}
