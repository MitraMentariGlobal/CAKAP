package co.id.cakap.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import co.id.cakap.data.NotificationApiData;
import co.id.cakap.data.NotificationData;
import co.id.cakap.ui.dashboard.activity.activityInvToMb.ActivityInvToMbPresenter;
import co.id.cakap.ui.dashboard.notification.NotificationPresenter;
import co.id.cakap.utils.Logger;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private List<NotificationApiData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public NotificationAdapter(List<NotificationApiData> resultData, Context context){
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
        NotificationApiData notificationApiData = mResultData.get(position);

        holder.position = position;
        holder.context = mContext;
        holder.notificationApiData = notificationApiData;
        holder.mNotificationTitle.setText(notificationApiData.getTitle());
        holder.mNotificationDesc.setText(notificationApiData.getBody());
        holder.mNotificationDescExpand.setText(notificationApiData.getBody());
        holder.mDate.setText(notificationApiData.getCreated());

        if (notificationApiData.getStatus().equals("1")) {
            holder.mNotificationTitle.setTextColor(mContext.getResources().getColor(R.color.curated));
            holder.mNotificationDesc.setTextColor(mContext.getResources().getColor(R.color.curated));
            holder.mNotificationDescExpand.setTextColor(mContext.getResources().getColor(R.color.curated));
            holder.mDate.setTextColor(mContext.getResources().getColor(R.color.curated));
            holder.mImgUnread.setVisibility(View.GONE);
        } else {
            holder.mNotificationTitle.setTextColor(mContext.getResources().getColor(R.color.black));
            holder.mNotificationDesc.setTextColor(mContext.getResources().getColor(R.color.black));
            holder.mNotificationDescExpand.setTextColor(mContext.getResources().getColor(R.color.black));
            holder.mDate.setTextColor(mContext.getResources().getColor(R.color.black));
            holder.mImgUnread.setVisibility(View.VISIBLE);
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
        @BindView(R.id.img_unread)
        ImageView mImgUnread;

        int position;
        Context context;
        NotificationApiData notificationApiData;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mExpandableLayout.collapse();
        }

        @OnClick(R.id.linear_parent)
        public void openDetail() {
            if (mExpandableLayout.isExpanded()) {
                Logger.d("isExpanded");
                mExpandableLayout.collapse();
                mNotificationDesc.setVisibility(View.VISIBLE);
            } else {
                Logger.d("not Expanded");
                Logger.d("notificationData id : " + notificationApiData.getId());
                if (notificationApiData.getStatus().equals("0")) {
                    notificationApiData.setStatus("1");
                    mNotificationTitle.setTextColor(mContext.getResources().getColor(R.color.curated));
                    mNotificationDesc.setTextColor(mContext.getResources().getColor(R.color.curated));
                    mNotificationDescExpand.setTextColor(mContext.getResources().getColor(R.color.curated));
                    mDate.setTextColor(mContext.getResources().getColor(R.color.curated));
                    mImgUnread.setVisibility(View.GONE);
                    new NotificationPresenter().readItemNotification(notificationApiData.getId());
//                    notifyDataSetChanged();
                }

//                notifyDataSetChanged();
                mExpandableLayout.expand();
                mNotificationDesc.setVisibility(View.GONE);

            }
        }
    }
}
