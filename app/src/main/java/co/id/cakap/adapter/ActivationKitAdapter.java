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
import co.id.cakap.data.ActivationKitData;
import co.id.cakap.data.BankInfoData;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class ActivationKitAdapter extends RecyclerView.Adapter<ActivationKitAdapter.ViewHolder> {
    private List<ActivationKitData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ActivationKitAdapter(List<ActivationKitData> resultData, Context context){
        mResultData = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_activation_number_form, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ActivationKitData activationKitData = mResultData.get(position);

        holder.context = mContext;
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_item_kit)
        TextView mTxtItemKit;
        @BindView(R.id.main_list)
        RecyclerView mRecyclerView;

        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
