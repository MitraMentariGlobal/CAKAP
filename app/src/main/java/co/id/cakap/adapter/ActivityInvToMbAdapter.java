package co.id.cakap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.id.cakap.R;
import co.id.cakap.data.ActivityInvToMbData;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class ActivityInvToMbAdapter extends RecyclerView.Adapter<ActivityInvToMbAdapter.ViewHolder> {
    private List<ActivityInvToMbData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ActivityInvToMbAdapter(List<ActivityInvToMbData> resultData, Context context){
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
        ActivityInvToMbData activityInvToMbData = mResultData.get(position);

        holder.mLinearAction.setVisibility(View.GONE);
        holder.mTotalPv.setText(activityInvToMbData.getTotal_pv());
        holder.mTransactionId.setText(activityInvToMbData.getTransaction_id());
        holder.mDate.setText(activityInvToMbData.getDate());
        holder.mMemberId.setText(activityInvToMbData.getMember_id());
        holder.mTotalAmount.setText(activityInvToMbData.getTotal_amount());
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
        LinearLayout mLinearAction;

        public ViewHolder(View itemView) {
            super(itemView);
            mTotalPv = itemView.findViewById(R.id.txt_total_pv);
            mTransactionId = itemView.findViewById(R.id.txt_transaction_id);
            mDate = itemView.findViewById(R.id.txt_date);
            mMemberId = itemView.findViewById(R.id.txt_member_id);
            mName = itemView.findViewById(R.id.txt_name);
            mTotalAmount = itemView.findViewById(R.id.txt_total_amount);
            mLinearAction = itemView.findViewById(R.id.linear_action);
        }
    }
}
