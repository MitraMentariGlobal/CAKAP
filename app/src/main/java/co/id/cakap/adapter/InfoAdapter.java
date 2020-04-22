package co.id.cakap.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.cakap.R;
import co.id.cakap.data.InfoData;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {
    private List<InfoData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private boolean isBold;

    public InfoAdapter(List<InfoData> resultData, Context context, boolean isBold){
        mResultData = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.isBold = isBold;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_info, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InfoData infoData = mResultData.get(position);

        holder.context = mContext;
        holder.mTitle.setText(infoData.getTxt());
        holder.mValue.setText(infoData.getValue());

        if (isBold) {
            holder.mTitle.setTypeface(null, Typeface.BOLD);
            holder.mValue.setTypeface(null, Typeface.BOLD);
        } else {
            holder.mTitle.setTypeface(null, Typeface.NORMAL);
            holder.mValue.setTypeface(null, Typeface.NORMAL);
        }
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.value)
        TextView mValue;

        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
