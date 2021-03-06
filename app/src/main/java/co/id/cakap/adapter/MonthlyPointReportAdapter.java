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
import co.id.cakap.data.MonthlyPointReportData;
import co.id.cakap.data.StockCardData;
import co.id.cakap.utils.Utils;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class MonthlyPointReportAdapter extends RecyclerView.Adapter<MonthlyPointReportAdapter.ViewHolder> {
    private List<MonthlyPointReportData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public MonthlyPointReportAdapter(List<MonthlyPointReportData> resultData, Context context){
        mResultData = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_monthly_point_report, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MonthlyPointReportData monthlyPointReportData = mResultData.get(position);

        holder.context = mContext;
        holder.mDistrId.setText(monthlyPointReportData.getDistr_id());
        holder.mName.setText(monthlyPointReportData.getName());
        holder.mRank.setText(monthlyPointReportData.getRank());
        holder.mPpv.setText(monthlyPointReportData.getPpv());
        holder.mNgpv.setText(monthlyPointReportData.getNgpv());
        holder.mTgpv.setText(Utils.priceFromString(monthlyPointReportData.getTgpv()));
        holder.mStatus.setText(monthlyPointReportData.getStatus());

        if (monthlyPointReportData.getStatus().equals("TERMINATE")) {
            holder.mStatus.setTextColor(mContext.getResources().getColor(R.color.red));
        }

        holder.mTxtWordingAppvYearly.setText(monthlyPointReportData.getStr1());
        holder.mTxtWordingAtgpvYearly.setText(monthlyPointReportData.getStr2());
        holder.mTxtWordingAtgpv.setText(monthlyPointReportData.getStr3());

        holder.mTxtAppvYearly.setText(monthlyPointReportData.getKa1());
        holder.mTxtAtgpvYearly.setText(monthlyPointReportData.getKa2());
        holder.mTxtAtgpv.setText(monthlyPointReportData.getKa3());
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_distr_id)
        TextView mDistrId;
        @BindView(R.id.txt_name)
        TextView mName;
        @BindView(R.id.txt_status)
        TextView mStatus;
        @BindView(R.id.txt_rank)
        TextView mRank;
        @BindView(R.id.txt_ppv)
        TextView mPpv;
        @BindView(R.id.txt_ngpv)
        TextView mNgpv;
        @BindView(R.id.txt_tgpv)
        TextView mTgpv;

        @BindView(R.id.txt_wording_appv_yearly)
        TextView mTxtWordingAppvYearly;
        @BindView(R.id.txt_appv_yearly)
        TextView mTxtAppvYearly;

        @BindView(R.id.txt_wording_atgpv_yearly)
        TextView mTxtWordingAtgpvYearly;
        @BindView(R.id.txt_atgpv_yearly)
        TextView mTxtAtgpvYearly;

        @BindView(R.id.txt_wording_atgpv)
        TextView mTxtWordingAtgpv;
        @BindView(R.id.txt_atgpv)
        TextView mTxtAtgpv;

        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
