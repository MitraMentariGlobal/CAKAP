package co.id.cakap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.id.cakap.R;
import co.id.cakap.data.ActivityCashbillData;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class ActivityCashbillAdapter extends RecyclerView.Adapter<ActivityCashbillAdapter.ViewHolder> {
    private List<ActivityCashbillData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ActivityCashbillAdapter(List<ActivityCashbillData> resultData, Context context){
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
        ActivityCashbillData activityCashbillData = mResultData.get(position);

        holder.mItemVerified.setVisibility(View.GONE);
        holder.mTotalPv.setText(activityCashbillData.getTotal_pv());
        holder.mTransactionId.setText(activityCashbillData.getTransaction_id());
        holder.mDate.setText(activityCashbillData.getDate());
        holder.mMemberId.setText(activityCashbillData.getMember_id());
        holder.mTotalAmount.setText(activityCashbillData.getTotal_amount());
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
        ImageView mItemVerified;

        public ViewHolder(View itemView) {
            super(itemView);
            mTotalPv = itemView.findViewById(R.id.txt_total_pv);
            mTransactionId = itemView.findViewById(R.id.txt_transaction_id);
            mDate = itemView.findViewById(R.id.txt_date);
            mMemberId = itemView.findViewById(R.id.txt_member_id);
            mName = itemView.findViewById(R.id.txt_name);
            mTotalAmount = itemView.findViewById(R.id.txt_total_amount);
            mItemVerified = itemView.findViewById(R.id.item_verified);
        }
    }
}
