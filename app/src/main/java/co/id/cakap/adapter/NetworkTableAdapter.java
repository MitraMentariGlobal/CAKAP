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
import co.id.cakap.data.NetworkTableData;
import co.id.cakap.data.StockCardData;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class NetworkTableAdapter extends RecyclerView.Adapter<NetworkTableAdapter.ViewHolder> {
    private List<NetworkTableData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public NetworkTableAdapter(List<NetworkTableData> resultData, Context context){
        mResultData = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_network_table, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NetworkTableData networkTableData = mResultData.get(position);

        holder.context = mContext;
        holder.mMemberId.setText(networkTableData.getMember_id());
        holder.mMemberName.setText(networkTableData.getName_member());
        holder.mProvince.setText(networkTableData.getProvince());
        holder.mCity.setText(networkTableData.getCity());
        holder.mStatus.setText(networkTableData.getStatus());
        holder.mUpline.setText(networkTableData.getUpline());
        holder.mPosisi.setText(networkTableData.getPosisi());
        holder.mTupo.setText(networkTableData.getTupo());
        holder.mPpv.setText(networkTableData.getPpv());
        holder.mPpvreg.setText(networkTableData.getPpvreg());
        holder.mNgpv.setText(networkTableData.getNgpv());
        holder.mTgpv.setText(networkTableData.getTgpv());
        holder.mAtgpv.setText(networkTableData.getAtgpv());
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
        @BindView(R.id.txt_province)
        TextView mProvince;
        @BindView(R.id.txt_city)
        TextView mCity;
        @BindView(R.id.txt_status)
        TextView mStatus;
        @BindView(R.id.txt_upline)
        TextView mUpline;
        @BindView(R.id.txt_posisi)
        TextView mPosisi;
        @BindView(R.id.txt_tupo)
        TextView mTupo;
        @BindView(R.id.txt_ppv)
        TextView mPpv;
        @BindView(R.id.txt_ppvreg)
        TextView mPpvreg;
        @BindView(R.id.txt_ngpv)
        TextView mNgpv;
        @BindView(R.id.txt_tgpv)
        TextView mTgpv;
        @BindView(R.id.txt_atgpv)
        TextView mAtgpv;

        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
