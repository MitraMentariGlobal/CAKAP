/*
 * Copyright (C) 2017. Alexander Bilchuk <a.bilchuk@sandrlab.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package co.id.cakap.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.List;

import co.id.cakap.R;
import co.id.cakap.utils.widget.CustomRecyclerViewPager;

public class CustomViewPagerAdapter extends CustomRecyclerViewPager.CustomAdapter<CustomViewPagerAdapter.CustomViewHolder> {

    private List<String> dataList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public CustomViewPagerAdapter(@NonNull DisplayMetrics metrics, @NonNull List<String> dataList, Context context) {
        super(metrics);
        this.dataList = dataList;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.banner_item, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        Picasso.with(mContext)
                .load(dataList.get(position))
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class CustomViewHolder extends CustomRecyclerViewPager.CustomViewHolder {

        ImageView mImageView;

        public CustomViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_thumbnail);
        }
    }
}
