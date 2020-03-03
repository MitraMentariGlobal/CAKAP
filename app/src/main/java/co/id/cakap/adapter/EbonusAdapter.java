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
import co.id.cakap.R;
import co.id.cakap.data.EbonusData;
import co.id.cakap.data.StockCardData;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class EbonusAdapter extends RecyclerView.Adapter<EbonusAdapter.ViewHolder> {
    private List<EbonusData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public EbonusAdapter(List<EbonusData> resultData, Context context){
        mResultData = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_list_stock_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EbonusData ebonusData = mResultData.get(position);

        holder.context = mContext;
        holder.mDate.setText(ebonusData.getCreated());
        holder.mUserId.setText(ebonusData.getDescription());
        holder.mStockOut.setText(ebonusData.getNominal());
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_date)
        TextView mDate;
        @BindView(R.id.txt_user_id)
        TextView mUserId;
        @BindView(R.id.txt_saldo)
        TextView mSaldo;
        @BindView(R.id.txt_stock_in)
        TextView mStockIn;
        @BindView(R.id.txt_stock_out)
        TextView mStockOut;
        @BindView(R.id.linear_in)
        LinearLayout mLinearIn;
        @BindView(R.id.txt_out)
        TextView mTxtOut;

        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mLinearIn.setVisibility(View.GONE);
            mTxtOut.setVisibility(View.GONE);
        }
    }
}
