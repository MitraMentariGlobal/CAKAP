package co.id.cakap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.R;
import co.id.cakap.data.ActivityInvToMbData;
import co.id.cakap.data.NotificationData;
import co.id.cakap.ui.dashboard.activity.activityInvToMb.ActivityInvToMbPresenter;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private List<NotificationData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public NotificationAdapter(List<NotificationData> resultData, Context context){
        mResultData = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_list_notification, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NotificationData notificationData = mResultData.get(position);

        holder.context = mContext;
        holder.mNotificaionTitle.setText(notificationData.getNotification_title());
        holder.mNotificaionDesc.setText(notificationData.getNotification_desc());
        holder.mNotificaionDescExpand.setText(notificationData.getNotification_desc());
        holder.mDate.setText(notificationData.getDate());
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_notification_title)
        TextView mNotificaionTitle;
        @BindView(R.id.txt_notification_desc)
        TextView mNotificaionDesc;
        @BindView(R.id.txt_notification_desc_expand)
        TextView mNotificaionDescExpand;
        @BindView(R.id.txt_date)
        TextView mDate;
        @BindView(R.id.expandable_layout)
        ExpandableRelativeLayout mExpandableLayout;

        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mExpandableLayout.collapse();
        }

        @OnClick(R.id.linear_parent)
        public void openDetail() {
            if (mExpandableLayout.isExpanded()) {
                mExpandableLayout.collapse();
                mNotificaionDesc.setVisibility(View.VISIBLE);
            } else {
                mExpandableLayout.expand();
                mNotificaionDesc.setVisibility(View.GONE);
            }
        }
    }
}
