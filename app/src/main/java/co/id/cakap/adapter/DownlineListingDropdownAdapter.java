package co.id.cakap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.R;
import co.id.cakap.data.DownlineListingData;
import co.id.cakap.ui.downlineListing.DownlineListingPresenter;
import co.id.cakap.utils.Utils;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class DownlineListingDropdownAdapter extends RecyclerView.Adapter<DownlineListingDropdownAdapter.ViewHolder> {
    private List<Integer> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public DownlineListingDropdownAdapter(List<Integer> resultData, Context context){
        mResultData = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_downline_listing_dropdown, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int data = mResultData.get(position);

        holder.context = mContext;
        holder.level = data;
        holder.mTxtTitleLevel.setText("Level " + data);
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.action_expand_collapse)
        LinearLayout mActionExpandCollapse;
        @BindView(R.id.linear_list)
        LinearLayout mLinearList;
        @BindView(R.id.main_list)
        RecyclerView mMainList;
        @BindView(R.id.item_thumbnail)
        ImageView mImageIcon;
        @BindView(R.id.txt_title_level)
        TextView mTxtTitleLevel;

        Context context;
        int level = 0;
        private boolean mIsExpand = false;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.action_expand_collapse)
        public void actionExpandCollapse(View view) {
            if (mIsExpand) {
                Utils.collapse(mLinearList);
                mIsExpand = false;
                mImageIcon.animate().rotation(0).setDuration(500).start();
            } else {
                Utils.expand(mLinearList);
                mIsExpand = true;
                mImageIcon.animate().rotation(180).setDuration(500).start();

                new DownlineListingPresenter().getData(level, mMainList, mTxtTitleLevel);
            }
        }
    }
}
