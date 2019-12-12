package co.id.cakap.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import co.id.cakap.R;
import co.id.cakap.ui.dashboard.activity.activityBonusStatement.ActivityBonusStatementFragment;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillFragment;

public class SectionsActivityMemberPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{
            R.string.cashbill,
            R.string.bonus_statement
    };
    private final Context mContext;

    public SectionsActivityMemberPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new ActivityCashbillFragment();
                break;
            case 1:
                fragment = new ActivityBonusStatementFragment();
                break;
        }
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}