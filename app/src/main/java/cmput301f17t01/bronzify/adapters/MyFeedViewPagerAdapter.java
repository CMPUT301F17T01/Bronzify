package cmput301f17t01.bronzify.adapters;

/**
 * Created by Jacob on 2017-11-12.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cmput301f17t01.bronzify.fragments.MyFeedTabFeed;
import cmput301f17t01.bronzify.fragments.MyHistoryMapTab;

public class MyFeedViewPagerAdapter extends FragmentPagerAdapter {
    private final FragmentManager fm;

    /**
     * Creates the fragment manager
     *
     * @param fm
     */
    public MyFeedViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    /**
     * Gets the item in the list fragment
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            Bundle bundle = new Bundle();
            bundle.putString("type", "habitTypes");
            MyFeedTabFeed fragment = new MyFeedTabFeed();
            fragment.setArguments(bundle);
            return fragment;
        } else if (position == 1) {
            Bundle bundle = new Bundle();
            bundle.putString("type", "habitTypes");
            MyHistoryMapTab fragment = new MyHistoryMapTab();
            fragment.setArguments(bundle);
            return fragment;
        } else {
            return null;
        }
    }

    /**
     * Gets the count of items in the list fragment
     *
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }

}
