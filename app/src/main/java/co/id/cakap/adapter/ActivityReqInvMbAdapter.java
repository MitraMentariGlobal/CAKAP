package co.id.cakap.adapter;

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
import co.id.cakap.data.ActivityReqInvMbData;
import co.id.cakap.ui.dashboard.activity.activityReqInvMb.ActivityReqInvMbPresenter;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class ActivityReqInvMbAdapter extends RecyclerView.Adapter<ActivityReqInvMbAdapter.ViewHolder> {
    private List<ActivityReqInvMbData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ActivityReqInvMbAdapter(List<ActivityReqInvMbData> resultData, Context context){
        mResultData = resultData;
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
        ActivityReqInvMbData activityReqInvMbData = mResultData.get(position);

        holder.context = mContext;
        holder.mTotalPv.setText(activityReqInvMbData.getTotal_pv());
        holder.mTransactionId.setText(activityReqInvMbData.getTransaction_id());
        holder.mDate.setText(activityReqInvMbData.getDate());
        holder.mMemberId.setText(activityReqInvMbData.getMember_id());
        holder.mName.setText(activityReqInvMbData.getName());
        holder.mTotalAmount.setText(activityReqInvMbData.getTotal_amount());
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.relative_parent)
        public void openDetail() {
            new ActivityReqInvMbPresenter().getView().openDetailTransaction(mTransactionId.getText().toString());
        }

        @OnClick(R.id.item_cancel)
        public void actionReject() {
            Toast.makeText(context, "Reject", Toast.LENGTH_SHORT).show();
        }

        @OnClick(R.id.item_verified)
        public void actionApprove() {
            Toast.makeText(context, "Approve", Toast.LENGTH_SHORT).show();
        }
    }
}
