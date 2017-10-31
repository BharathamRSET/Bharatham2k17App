package com.roundmelon.jv.bharatham2k17;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Joseph on 02/10/17.
 */

public class PointsAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PointsAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ScoreFragment0 tab0 = new ScoreFragment0();
                return tab0;

            case 1:
                ScoreFragment1 tab1 = new ScoreFragment1();
                return tab1;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}