package cmput301f17t01.bronzify.adapters.recyclers;

import android.view.View;

/**
 * Created by owenm_000 on 12/2/2017.
 */

interface ClickListener {
    void onItemClick(int position, View v);
    void onItemLongClick(int position, View v);
}
