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
import co.id.cakap.R;
import co.id.cakap.data.DownlineListingData;
import co.id.cakap.data.StockCardData;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class DownlineListingAdapter extends RecyclerView.Adapter<DownlineListingAdapter.ViewHolder> {
    private List<DownlineListingData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public DownlineListingAdapter(List<DownlineListingData> resultData, Context context){
        mResultData = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_downline_listing, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DownlineListingData downlineListingData = mResultData.get(position);

        holder.context = mContext;
        holder.mMemberId.setText(downlineListingData.getMember_id());
        holder.mMemberName.setText(downlineListingData.getName());
        holder.mNomorHp.setText(downlineListingData.getNo_hp());
        holder.mJoinDate.setText(downlineListingData.getJoin_date());
        holder.mStatus.setText(downlineListingData.getStatus());
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_member_id)
        TextView mMemberId;
        @BindView(R.id.txt_member_name)
        TextView mMemberName;
        @BindView(R.id.txt_nomor_hp)
        TextView mNomorHp;
        @BindView(R.id.txt_join_date)
        TextView mJoinDate;
        @BindView(R.id.txt_status)
        TextView mStatus;

        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
