package cmput301f17t01.bronzify.adapters;

/*
 * Created by Jacob on 2017-11-12.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cmput301f17t01.bronzify.fragments.HabitTypeDetailFragment;
import cmput301f17t01.bronzify.fragments.HabitTypeEventFragment;

public class HabitTypeViewPagerAdapter extends FragmentPagerAdapter {
    private final FragmentManager fm;

    /**
     * Creates the fragment manager
     *
     * @param fm
     */
    public HabitTypeViewPagerAdapter(FragmentManager fm) {
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
            return new HabitTypeDetailFragment();
        }
        if (position == 1) {
//            Bundle bundle = new Bundle();
//            bundle.putString("type", "habitTypes");
//            ListFragment fragment = new ListFragment();
//            fragment.setArguments(bundle);
//            return fragment;
            return new HabitTypeEventFragment();
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
