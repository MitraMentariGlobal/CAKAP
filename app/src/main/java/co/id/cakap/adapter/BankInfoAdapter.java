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
import co.id.cakap.data.BankInfoData;
import co.id.cakap.data.NetworkTableData;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class BankInfoAdapter extends RecyclerView.Adapter<BankInfoAdapter.ViewHolder> {
    private List<BankInfoData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public BankInfoAdapter(List<BankInfoData> resultData, Context context){
        mResultData = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_info_bank, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BankInfoData bankInfoData = mResultData.get(position);

        holder.context = mContext;
        holder.mId.setText(bankInfoData.getId());
        holder.mAccountName.setText(bankInfoData.getAccountname());
        holder.mAccountNumber.setText(bankInfoData.getAccountno());
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_id)
        TextView mId;
        @BindView(R.id.txt_account_name)
        TextView mAccountName;
        @BindView(R.id.txt_account_number)
        TextView mAccountNumber;

        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
