package co.id.cakap.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.R;
import co.id.cakap.data.ActivityRekapBnsBcmbData;
import co.id.cakap.data.ActivityReqInvMbData;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillPresenter;
import co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb.ActivityRekapBnsBcmbPresenter;
import co.id.cakap.utils.dialog.UserConfirmationDialog;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class ActivityRekapBnsBcmbAdapter extends RecyclerView.Adapter<ActivityRekapBnsBcmbAdapter.ViewHolder> {
    private List<ActivityRekapBnsBcmbData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ActivityRekapBnsBcmbAdapter(List<ActivityRekapBnsBcmbData> resultData, Context context){
        mResultData = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_list_activity_rekap, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ActivityRekapBnsBcmbData activityRekapBnsBcmbData = mResultData.get(position);

        holder.context = mContext;
        holder.activityRekapBnsBcmbData = activityRekapBnsBcmbData;
        holder.mMemberId.setText(activityRekapBnsBcmbData.getMember_id());
        holder.mName.setText(activityRekapBnsBcmbData.getName());
        holder.mPhoneNumber.setText(activityRekapBnsBcmbData.getMobile_phone());
        holder.mAmount.setText("IDR " + activityRekapBnsBcmbData.getAmount());
        holder.mDate.setText(activityRekapBnsBcmbData.getDate());

        if (!(activityRekapBnsBcmbData.getFlag().equals("0"))) {
            holder.mItemVerified.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_member_id)
        TextView mMemberId;
        @BindView(R.id.txt_name)
        TextView mName;
        @BindView(R.id.txt_phone_number)
        TextView mPhoneNumber;
        @BindView(R.id.txt_amount)
        TextView mAmount;
        @BindView(R.id.txt_date)
        TextView mDate;
        @BindView(R.id.item_cancel)
        ImageView mItemCancel;
        @BindView(R.id.item_verified)
        ImageView mItemVerified;

        Context context;
        ActivityRekapBnsBcmbData activityRekapBnsBcmbData;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mItemCancel.setVisibility(View.GONE);
        }

        @OnClick(R.id.relative_parent)
        public void openDetail() {
//            new ActivityRekapBnsBcmbPresenter().getView().openDetailTransaction(mMemberId.getText().toString());
        }

        @OnClick(R.id.item_verified)
        public void actionApprove() {
            UserConfirmationDialog utils = new UserConfirmationDialog();
            Dialog dialog = utils.showDialog(context);
            utils.setRekapBonusDialog();
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
                    new ActivityRekapBnsBcmbPresenter().getView().openPinDialog(activityRekapBnsBcmbData);
                }
            });
        }

//        @OnClick(R.id.item_cancel)
//        public void actionCancel() {
//            UserConfirmationDialog utils = new UserConfirmationDialog();
//            Dialog dialog = utils.showDialog(context);
//            utils.setTitleDialogInvalidAccount("Invalid Acccount?");
//            utils.setNegativeAction();
//
//            dialog.findViewById(R.id.no_act_btn).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//
//            dialog.findViewById(R.id.yes_act_btn).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                    new ActivityRekapBnsBcmbPresenter().getView().openPinDialog(activityRekapBnsBcmbData);
//                }
//            });
//        }
    }
}
