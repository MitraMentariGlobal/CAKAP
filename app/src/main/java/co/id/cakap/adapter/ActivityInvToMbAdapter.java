package co.id.cakap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.R;
import co.id.cakap.data.ActivityInvToMbData;
import co.id.cakap.ui.dashboard.activity.activityInvToMb.ActivityInvToMbPresenter;

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

        holder.context = mContext;
        holder.mTotalPv.setText(activityInvToMbData.getTotal_pv());
        holder.mTransactionId.setText(activityInvToMbData.getTransaction_id());
        holder.mDate.setText(activityInvToMbData.getDate());
        holder.mMemberId.setText(activityInvToMbData.getMember_id());
        holder.mName.setText(activityInvToMbData.getName());
        holder.mTotalAmount.setText(activityInvToMbData.getTotal_amount());
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
        @BindView(R.id.linear_action)
        LinearLayout mLinearAction;

        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mLinearAction.setVisibility(View.GONE);
        }

        @OnClick(R.id.relative_parent)
        public void openDetail() {
            new ActivityInvToMbPresenter().getView().openDetailTransaction();
        }
    }
}
