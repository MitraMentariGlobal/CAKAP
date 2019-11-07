package co.id.cakap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.OnClick;
import co.id.cakap.R;
import co.id.cakap.data.RestockReceiveStockData;
import co.id.cakap.data.RestockReqInvoiceData;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class RestockReceiveStockAdapter extends RecyclerView.Adapter<RestockReceiveStockAdapter.ViewHolder> {
    private List<RestockReceiveStockData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public RestockReceiveStockAdapter(List<RestockReceiveStockData> resultData, Context context){
        mResultData = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_list_restock, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RestockReceiveStockData restockReceiveStockData = mResultData.get(position);

        holder.mStatus.setVisibility(View.GONE);
        holder.mTotalPv.setText(restockReceiveStockData.getTotal_pv());
        holder.mTransactionId.setText(restockReceiveStockData.getTransaction_id());
        holder.mDate.setText(restockReceiveStockData.getDate());
        holder.mTotalAmount.setText(restockReceiveStockData.getTotal_amount());

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
        TextView mStatus;
        TextView mTotalAmount;
        ImageView mItemVerified;

        public ViewHolder(View itemView) {
            super(itemView);
            mTotalPv = itemView.findViewById(R.id.txt_total_pv);
            mTransactionId = itemView.findViewById(R.id.txt_transaction_id);
            mDate = itemView.findViewById(R.id.txt_date);
            mTotalAmount = itemView.findViewById(R.id.txt_total_amount);
            mStatus = itemView.findViewById(R.id.txt_status);
            mItemVerified = itemView.findViewById(R.id.item_verified);
        }

        @OnClick(R.id.relative_parent)
        public void openDetail() {

        }
    }
}
