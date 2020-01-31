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
import co.id.cakap.ui.dashboard.notification.NotificationPresenter;

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

        holder.position = position;
        holder.context = mContext;
        holder.notificationData = notificationData;
        holder.mNotificationTitle.setText(notificationData.getNotification_title());
        holder.mNotificationDesc.setText(notificationData.getNotification_desc());
        holder.mNotificationDescExpand.setText(notificationData.getNotification_desc());
        holder.mDate.setText(notificationData.getDate());

        if (notificationData.getIsRead()) {
            holder.mNotificationTitle.setTextColor(mContext.getResources().getColor(R.color.curated));
            holder.mNotificationDesc.setTextColor(mContext.getResources().getColor(R.color.curated));
            holder.mNotificationDescExpand.setTextColor(mContext.getResources().getColor(R.color.curated));
            holder.mDate.setTextColor(mContext.getResources().getColor(R.color.curated));
        } else {
            holder.mNotificationTitle.setTextColor(mContext.getResources().getColor(R.color.black));
            holder.mNotificationDesc.setTextColor(mContext.getResources().getColor(R.color.black));
            holder.mNotificationDescExpand.setTextColor(mContext.getResources().getColor(R.color.black));
            holder.mDate.setTextColor(mContext.getResources().getColor(R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_notification_title)
        TextView mNotificationTitle;
        @BindView(R.id.txt_notification_desc)
        TextView mNotificationDesc;
        @BindView(R.id.txt_notification_desc_expand)
        TextView mNotificationDescExpand;
        @BindView(R.id.txt_date)
        TextView mDate;
        @BindView(R.id.expandable_layout)
        ExpandableRelativeLayout mExpandableLayout;

        int position;
        Context context;
        NotificationData notificationData;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mExpandableLayout.collapse();
        }

        @OnClick(R.id.linear_parent)
        public void openDetail() {
            if (mExpandableLayout.isExpanded()) {
                mExpandableLayout.collapse();
                mNotificationDesc.setVisibility(View.VISIBLE);
            } else {
                mExpandableLayout.expand();
                mNotificationDesc.setVisibility(View.GONE);

                if (!notificationData.getIsRead())
                    new NotificationPresenter().changeReadStatus(notificationData, position);
            }
        }
    }
}
