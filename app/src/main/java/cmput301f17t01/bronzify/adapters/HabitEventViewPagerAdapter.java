package cmput301f17t01.bronzify.adapters;

/**
 * Created by Jacob on 2017-11-12.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cmput301f17t01.bronzify.fragments.HabitEventDetailFragment;
import cmput301f17t01.bronzify.fragments.ListFragment;
import cmput301f17t01.bronzify.fragments.MapFragment;
import cmput301f17t01.bronzify.fragments.PictureFragment;

public class HabitEventViewPagerAdapter extends FragmentPagerAdapter {
    private FragmentManager fm;

    /**
     * Creates the fragment manager
     *
     * @param fm
     */
    public HabitEventViewPagerAdapter(FragmentManager fm) {
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
            return new HabitEventDetailFragment();
        } else if (position == 1){
            return new MapFragment();
        } else if (position == 2){
            return new PictureFragment();
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
        return 3;
    }
}
