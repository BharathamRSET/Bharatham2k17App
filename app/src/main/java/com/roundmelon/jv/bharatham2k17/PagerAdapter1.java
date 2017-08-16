package com.roundmelon.jv.bharatham2k17;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by DELL on 16-08-2016.
 */

public class PagerAdapter1 extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter1(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Tab0 tab0 = new Tab0();
                return tab0;

            case 1:
                Tab1 tab1 = new Tab1();
                return tab1;

            case 2:
                Tab2 tab2 = new Tab2();
                return tab2;

            case 3:
                Tab3 tab3 = new Tab3();
                return tab3;

            case 4:
                Tab4 tab4 = new Tab4();
                return tab4;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}