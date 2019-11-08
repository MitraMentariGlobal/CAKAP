package co.id.cakap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.R;
import co.id.cakap.data.ActivityRekapBnsBcmbData;
import co.id.cakap.data.ActivityReqInvMbData;
import co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb.ActivityRekapBnsBcmbPresenter;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
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
        holder.mMemberId.setText(activityRekapBnsBcmbData.getMember_id());
        holder.mName.setText(activityRekapBnsBcmbData.getName());
        holder.mPhoneNumber.setText(activityRekapBnsBcmbData.getMobile_phone());
        holder.mAmount.setText(activityRekapBnsBcmbData.getAmount());
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

        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.relative_parent)
        public void openDetail() {
            new ActivityRekapBnsBcmbPresenter().getView().openDetailTransaction();
        }
    }
}
