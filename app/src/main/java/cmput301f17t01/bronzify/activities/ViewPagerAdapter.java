package cmput301f17t01.bronzify.activities;

/**
 * Created by Jacob on 2017-11-12.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cmput301f17t01.bronzify.fragments.HabitEventDetailFragment;
import cmput301f17t01.bronzify.fragments.HabitTypeDetailFragment;
import cmput301f17t01.bronzify.fragments.HabitTypeEventFragment;
import cmput301f17t01.bronzify.fragments.MapFragment;
import cmput301f17t01.bronzify.fragments.PictureFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HabitEventDetailFragment();
        } else if (position == 1){
            return new MapFragment();
        } else {
            return new MapFragment(); //Picture
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
