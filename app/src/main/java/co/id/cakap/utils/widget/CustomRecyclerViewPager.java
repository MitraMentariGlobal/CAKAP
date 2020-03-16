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
package co.id.cakap.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import co.id.cakap.R;

public class CustomRecyclerViewPager extends RecyclerView {

    private int itemMargin;

    public CustomRecyclerViewPager(Context context) {
        super(context);
        init(context, null);
    }

    public CustomRecyclerViewPager(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomRecyclerViewPager(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomRecyclerViewPager, 0, 0);
            itemMargin = (int) typedArray.getDimension(R.styleable.CustomRecyclerViewPager_itemMargin, 0f);
            typedArray.recycle();
        }

        setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(this);
    }

    public void setAdapter(Adapter adapter) {
        if (CustomAdapter.class.isInstance(adapter)) {
            CustomAdapter customAdapter = (CustomAdapter) adapter;
            customAdapter.setItemMargin(itemMargin);
            customAdapter.updateDisplayMetrics();
        } else {
            throw new IllegalArgumentException("Only CustomAdapter is allowed here");
        }
        super.setAdapter(adapter);
    }

    public static abstract class CustomAdapter<VH extends CustomViewHolder> extends RecyclerView.Adapter<VH> {

        private DisplayMetrics metrics;
        private int itemMargin;
        private int itemWidth;

        public CustomAdapter(@NonNull DisplayMetrics metrics) {
            this.metrics = metrics;
        }

        void setItemMargin(int itemMargin) {
            this.itemMargin = itemMargin;
        }

        void updateDisplayMetrics() {
            itemWidth = metrics.widthPixels - itemMargin * 2;
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            int currentItemWidth = itemWidth;

            System.out.println("itemMargin : " + itemMargin);

            if (position == 0) {
                currentItemWidth += itemMargin;
                holder.rootLayout.setPadding(itemMargin, 0, 0, 0);
            } else if (position == getItemCount() - 1) {
                currentItemWidth += itemMargin;
                holder.rootLayout.setPadding(0, 0, itemMargin, 0);
            }

            int height = holder.rootLayout.getLayoutParams().height;
            holder.rootLayout.setLayoutParams(new ViewGroup.LayoutParams(currentItemWidth, height));
        }
    }

    public static abstract class CustomViewHolder extends RecyclerView.ViewHolder {

        ViewGroup rootLayout;

        public CustomViewHolder(View itemView) {
            super(itemView);
            rootLayout = (ViewGroup) itemView.findViewById(R.id.root_layout);
        }
    }
}
