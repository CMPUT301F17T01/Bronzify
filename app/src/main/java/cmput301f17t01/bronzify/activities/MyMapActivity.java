package cmput301f17t01.bronzify.activities;

import android.os.Bundle;
import android.app.Activity;

import cmput301f17t01.bronzify.R;

/**
 * Created by jblazusi on 2017-11-01.
 */
public class MyMapActivity extends Activity {

    private Integer radius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_map);
    }
}
