package cmput301f17t01.bronzify.adapters;

/**
 * Created by Jacob on 2017-11-12.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.fragments.HabitEventDetailFragment;
import cmput301f17t01.bronzify.fragments.HabitTypeDetailFragment;
import cmput301f17t01.bronzify.fragments.HabitTypeEventFragment;
import cmput301f17t01.bronzify.fragments.ListFragment;
import cmput301f17t01.bronzify.fragments.MapFragment;
import cmput301f17t01.bronzify.fragments.PictureFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private FragmentManager fm;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;

    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            Bundle bundle = new Bundle();
            bundle.putString("type", "pendingFollows");
            ListFragment fragment = new ListFragment();
            fragment.setArguments(bundle);

            return fragment;
        } else {
            return new MapFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
