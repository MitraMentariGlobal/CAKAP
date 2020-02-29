package co.id.cakap.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.R;
import co.id.cakap.data.ActivityCashbillData;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillPresenter;
import co.id.cakap.ui.login.LoginActivity;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.NewAddressDialog;
import co.id.cakap.utils.dialog.UserConfirmationDialog;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class ActivityCashbillAdapter extends RecyclerView.Adapter<ActivityCashbillAdapter.ViewHolder> implements Filterable {
    private List<ActivityCashbillData> mResultData;
    private List<ActivityCashbillData> mFilteredList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ActivityCashbillAdapter(List<ActivityCashbillData> resultData, Context context){
        mResultData = resultData;
        mFilteredList = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_list_activity, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ActivityCashbillData activityCashbillData = mFilteredList.get(position);

        if (Constant.LOGIN_DATA.equals(mContext.getResources().getString(R.string.member_login))) {
            holder.mItemCancel.setVisibility(View.GONE);
        }

        holder.context = mContext;
        holder.activityCashbillData = activityCashbillData;
        holder.mTotalPv.setText(activityCashbillData.getTotal_pv());
        holder.mTransactionId.setText(activityCashbillData.getTransaction_id());
        holder.mDate.setText(activityCashbillData.getDate());
        holder.mMemberId.setText(activityCashbillData.getMember_id() + " - " + activityCashbillData.getName());
        holder.mName.setText(activityCashbillData.getName());
        holder.mTotalAmount.setText("IDR " + activityCashbillData.getTotal_amount());

        if (!(activityCashbillData.getKit().equals("N") && activityCashbillData.getNote().equals("0"))) {
            holder.mItemCancel.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredList = mResultData;
                } else {
                    ArrayList<ActivityCashbillData> filteredList = new ArrayList<>();
                    for (ActivityCashbillData activityCashbillData : mResultData) {
                        if (activityCashbillData.getTransaction_id().toLowerCase().contains(charString) ||
                                activityCashbillData.getMember_id().toLowerCase().contains(charString) ||
                                activityCashbillData.getName().toLowerCase().contains(charString)) {
                            filteredList.add(activityCashbillData);
                        }
                    }
                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<ActivityCashbillData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_total_pv)
        TextView mTotalPv;
        @BindView(R.id.txt_transaction_id)
        TextView mTransactionId;
        @BindView(R.id.txt_date)
        TextView mDate;
        @BindView(R.id.txt_member_id)
        TextView mMemberId;
        @BindView(R.id.txt_name)
        TextView mName;
        @BindView(R.id.txt_total_amount)
        TextView mTotalAmount;
        @BindView(R.id.item_cancel)
        ImageView mItemCancel;
        @BindView(R.id.item_verified)
        ImageView mItemVerified;

        Context context;
        ActivityCashbillData activityCashbillData;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mItemVerified.setVisibility(View.GONE);
        }

        @OnClick(R.id.relative_parent)
        public void openDetail() {
            new ActivityCashbillPresenter().getView().openDetailTransaction(activityCashbillData);
        }

        @OnClick(R.id.item_cancel)
        public void actionCancel() {
            UserConfirmationDialog utils = new UserConfirmationDialog();
            Dialog dialog = utils.showDialog(context);
            utils.setTitleDialog("Cancel");
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
                    new ActivityCashbillPresenter().getView().openPinDialog(activityCashbillData);
                }
            });
        }
    }
}
