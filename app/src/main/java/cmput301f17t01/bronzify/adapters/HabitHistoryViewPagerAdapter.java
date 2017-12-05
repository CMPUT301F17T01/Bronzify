package cmput301f17t01.bronzify.adapters;

/**
 * Created by Jacob on 2017-11-12.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cmput301f17t01.bronzify.activities.MyMapActivity;
import cmput301f17t01.bronzify.fragments.HabitHistoryTabFeed;
import cmput301f17t01.bronzify.fragments.MapFragment;

public class HabitHistoryViewPagerAdapter extends FragmentPagerAdapter {
    private FragmentManager fm;

    /**
     * Creates the fragment manager
     *
     * @param fm
     */
    public HabitHistoryViewPagerAdapter(FragmentManager fm) {
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
            return new HabitHistoryTabFeed();
        } else if (position == 1){
            return new MapFragment();
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
