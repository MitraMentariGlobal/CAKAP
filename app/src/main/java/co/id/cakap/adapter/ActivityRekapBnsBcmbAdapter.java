package co.id.cakap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.OnClick;
import co.id.cakap.R;
import co.id.cakap.data.ActivityRekapBnsBcmbData;
import co.id.cakap.data.ActivityReqInvMbData;

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

        TextView mMemberId;
        TextView mName;
        TextView mPhoneNumber;
        TextView mAmount;

        public ViewHolder(View itemView) {
            super(itemView);
            mMemberId = itemView.findViewById(R.id.txt_member_id);
            mName = itemView.findViewById(R.id.txt_name);
            mPhoneNumber = itemView.findViewById(R.id.txt_phone_number);
            mAmount = itemView.findViewById(R.id.txt_amount);
        }

        @OnClick(R.id.relative_parent)
        public void openDetail() {

        }
    }
}
