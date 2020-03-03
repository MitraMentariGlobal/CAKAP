package co.id.cakap.ui.dashboard.notification;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.NotificationAdapter;
import co.id.cakap.data.NotificationData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.utils.dialog.UserConfirmationDialog;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class NotificationFragment extends Fragment implements NotificationContract.View {
    @Inject
    NotificationPresenter mNotificationPresenter;

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.notification_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.linear_empty_notifications)
    LinearLayout mLinearEmptyNotifications;

    private View mView;
    private Unbinder mUnbinder;
    private NotificationAdapter mListAdapter;
    private SharedPreferences mSharedPreferences;
    private NotificationContract.UserActionListener mUserActionListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_notification, container, false);
            mUnbinder = ButterKnife.bind(this, mView);

            setupActivityComponent();
            initializeData();
        }

        ButterKnife.bind(this, mView);
        return mView;
    }

    private void setupActivityComponent() {
        CoreApp.get()
                .getAppComponent()
                .plus(new MainActivityModule(this))
                .inject(this);
    }

    @Override
    public void initializeData() {
        mUserActionListener = mNotificationPresenter;
        mNotificationPresenter.setView(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        mUserActionListener.getData(mSharedPreferences);

        mToolbar.setTitle("");
        mTitle.setText(getContext().getResources().getString(R.string.notification).toUpperCase());
    }

    @Override
    public void setAdapter(List<NotificationData> resultData) {
        if (resultData.isEmpty()) {
            mRecyclerView.setVisibility(View.GONE);
            mLinearEmptyNotifications.setVisibility(View.VISIBLE);
        } else {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            mRecyclerView.setVisibility(View.VISIBLE);
            mLinearEmptyNotifications.setVisibility(View.GONE);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(layoutManager);
            mListAdapter = new NotificationAdapter(resultData, getContext());
            mRecyclerView.setAdapter(mListAdapter);
            OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        }

        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        mRelativeProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mRelativeProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setErrorResponse(String message) {
        try {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateList() {
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.action_bar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_mark_read:
                mNotificationPresenter.readAllData();
                return true;

            case R.id.action_delete_all:
                openDialogNotification();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void openDialogNotification() {
        UserConfirmationDialog utils = new UserConfirmationDialog();
        Dialog dialog = utils.showDialog(getContext());
        utils.setTitleDeleteAllNotification();
        utils.setNegativeAction();

        dialog.findViewById(R.id.no_act_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.yes_act_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showProgressBar();
                mUserActionListener.deleteAllNotification();
            }
        });
    }
}
