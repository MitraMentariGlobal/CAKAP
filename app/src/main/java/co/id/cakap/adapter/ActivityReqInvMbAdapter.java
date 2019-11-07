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

import butterknife.OnClick;
import co.id.cakap.R;
import co.id.cakap.data.ActivityReqInvMbData;

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

        holder.mTotalPv.setText(activityReqInvMbData.getTotal_pv());
        holder.mTransactionId.setText(activityReqInvMbData.getTransaction_id());
        holder.mDate.setText(activityReqInvMbData.getDate());
        holder.mMemberId.setText(activityReqInvMbData.getMember_id());
        holder.mTotalAmount.setText(activityReqInvMbData.getTotal_amount());

        holder.mItemCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(mContext, "Reject", Toast.LENGTH_SHORT).show();
            }
        });

        holder.mItemVerified.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(mContext, "Approve", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTotalPv;
        TextView mTransactionId;
        TextView mDate;
        TextView mMemberId;
        TextView mName;
        TextView mTotalAmount;
        ImageView mItemCancel;
        ImageView mItemVerified;

        public ViewHolder(View itemView) {
            super(itemView);
            mTotalPv = itemView.findViewById(R.id.txt_total_pv);
            mTransactionId = itemView.findViewById(R.id.txt_transaction_id);
            mDate = itemView.findViewById(R.id.txt_date);
            mMemberId = itemView.findViewById(R.id.txt_member_id);
            mName = itemView.findViewById(R.id.txt_name);
            mTotalAmount = itemView.findViewById(R.id.txt_total_amount);
            mItemCancel = itemView.findViewById(R.id.item_cancel);
            mItemVerified = itemView.findViewById(R.id.item_verified);
        }

        @OnClick(R.id.relative_parent)
        public void openDetail() {

        }
    }
}
