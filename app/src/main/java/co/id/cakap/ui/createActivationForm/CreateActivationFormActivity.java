package co.id.cakap.ui.createActivationForm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.ActivationKitAdapter;
import co.id.cakap.adapter.ActivityRekapBnsBcmbAdapter;
import co.id.cakap.adapter.ItemShopCashbillAdapter;
import co.id.cakap.data.ActivationKitData;
import co.id.cakap.data.ActivationSubmitItemFormData;
import co.id.cakap.data.ActivityInvToMbData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.BottomDialogActivity;
import co.id.cakap.utils.dialog.PinDialog;
import co.id.cakap.utils.dialog.UserConfirmationDialog;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class CreateActivationFormActivity extends AppCompatActivity implements CreateActivationFormContract.View{
    @Inject
    CreateActivationFormPresenter mCreateActivationFormPresenter;

    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.title_toolbar)
    TextView mTitleToolbar;
    @BindView(R.id.txt_transaction_id)
    TextView mTxtTransactionId;
    @BindView(R.id.txt_mb_id)
    TextView mTxtMbId;
    @BindView(R.id.txt_name)
    TextView mTxtName;
    @BindView(R.id.nested_scroll)
    NestedScrollView mNestedScroll;

    private String mTitle = "";
    private ActivityInvToMbData mActivityInvToMbData;
    private ActivationKitAdapter mActivationKitAdapter;
    private List<ActivationSubmitItemFormData> mSubmitItemFormDataList = new ArrayList<>();
    private CreateActivationFormContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_activation_form);
        ButterKnife.bind(this);

        setupActivityComponent();
        initializeData();
    }

    private void setupActivityComponent() {
        CoreApp.get()
                .getAppComponent()
                .plus(new MainActivityModule(this))
                .inject(this);
    }

    @Override
    public void initializeData() {
        mUserActionListener = mCreateActivationFormPresenter;
        mCreateActivationFormPresenter.setView(this);

        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra(Constant.INVOICE_TRANSACTION_DATA);

        mTitle = intent.getStringExtra(Constant.TITLE_DETAIL);
        mActivityInvToMbData = b.getParcelable(Constant.INVOICE_TRANSACTION_DATA);

        mTitleToolbar.setText(mTitle.toUpperCase());
        mTxtTransactionId.setText(mActivityInvToMbData.getTransaction_id());
        mTxtMbId.setText(mActivityInvToMbData.getMember_id());
        mTxtName.setText(mActivityInvToMbData.getNama());

        mUserActionListener.getData(mActivityInvToMbData.getItem_id());
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAdapter(List<ActivationKitData> resultData) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mNestedScroll.getParent().requestChildFocus(mNestedScroll, mNestedScroll);

        mActivationKitAdapter = new ActivationKitAdapter(resultData, this);
        mRecyclerView.setAdapter(mActivationKitAdapter);

        hideProgressBar();
    }

    @Override
    public void insertOrUpdateItemData(ActivationSubmitItemFormData activationSubmitItemFormData) {
        if (mSubmitItemFormDataList.isEmpty()) {
            mSubmitItemFormDataList.add(activationSubmitItemFormData);
        } else {
            int dataSize = mSubmitItemFormDataList.size();
            int indexSame = -1;
            boolean isSame = false;

            for (int i = 0; i < dataSize; i++) {
                if (mSubmitItemFormDataList.get(i).getItemId().equals(activationSubmitItemFormData.getItemId())) {
                    if (mSubmitItemFormDataList.get(i).getNumberForm().equals(activationSubmitItemFormData.getNumberForm())) {
                        indexSame = i;
                        isSame = true;
                    }
                }

                if (i == dataSize - 1) {
                    if (isSame) {
                        mSubmitItemFormDataList.remove(indexSame);
                        mSubmitItemFormDataList.add(activationSubmitItemFormData);
                    } else {
                        mSubmitItemFormDataList.add(activationSubmitItemFormData);
                    }
                }
            }
        }
    }

    @Override
    public void setSuccessResponse() {
        hideProgressBar();

        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void openPinDialog() {
        PinDialog utils = new PinDialog();
        Dialog dialog = utils.showDialog(this);

        PinLockView pinLockView = dialog.findViewById(R.id.pin_lock_view);
        IndicatorDots indicatorDots = dialog.findViewById(R.id.indicator_dots);
        PinLockListener pinLockListener = new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                Logger.d("Pin complete: " + pin);
                dialog.hide();
                dialog.dismiss();

                mUserActionListener.submitData(pin, mActivityInvToMbData, mSubmitItemFormDataList);
            }

            @Override
            public void onEmpty() {
                Logger.d("Pin empty");
            }

            @Override
            public void onPinChange(int pinLength, String intermediatePin) {
                Logger.d("Pin changed, new length " + pinLength + " with intermediate pin " + intermediatePin);
            }
        };

        pinLockView.attachIndicatorDots(indicatorDots);
        pinLockView.setPinLockListener(pinLockListener);

        pinLockView.setPinLength(6);
        pinLockView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        indicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);
    }

    @OnClick(R.id.text_process)
    public void actionProcess(View view) {
        UserConfirmationDialog utils = new UserConfirmationDialog();
        Dialog dialog = utils.showDialog(this);
        utils.setTitleDialog();
        utils.setNegativeActionGreen();

        TextView txtNo = (TextView) dialog.findViewById(R.id.no_act_btn);
        txtNo.setText("Periksa Kembali");
        dialog.findViewById(R.id.no_act_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView txtYes = (TextView) dialog.findViewById(R.id.yes_act_btn);
        txtYes.setText("Bersedia");
        dialog.findViewById(R.id.yes_act_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                openPinDialog();
            }
        });
    }
}
