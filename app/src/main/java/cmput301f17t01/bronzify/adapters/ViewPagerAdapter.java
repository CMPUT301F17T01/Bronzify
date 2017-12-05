package cmput301f17t01.bronzify.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cmput301f17t01.bronzify.fragments.ListFragment;

/*
 * Created by Jacob on 2017-11-12.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final FragmentManager fm;

    /**
     * Creates the fragment manager
     *
     * @param fm
     */
    public ViewPagerAdapter(FragmentManager fm) {
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
            bundle.putString("type", "pendingFollows");
            ListFragment fragment = new ListFragment();
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
        return 1;
    }
}
