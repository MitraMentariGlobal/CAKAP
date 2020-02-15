package co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb;

import android.content.Context;

import java.util.List;

import co.id.cakap.adapter.ActivityRekapBnsBcmbAdapter;
import co.id.cakap.data.ActivityRekapBnsBcmbData;

public class ActivityRekapBnsBcmbContract {
    public interface View {
        void initializeData();
        void setAdapter(List<ActivityRekapBnsBcmbData> resultData);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void openDetailTransaction(String transactionId);
    }

    public interface UserActionListener {
        void getData(String tahun, String bulan);
    }
}
