package cmput301f17t01.bronzify.adapters.recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.controllers.ElasticSearch;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by owenm_000 on 11/22/2017.
 */

public class MyFollowersAdapter extends RecyclerView.Adapter<MyFollowersAdapter.ViewHolder> {

    private final Context mContext;
    private final List<String> entries;

    /**
     * This is the followers adapter that takes in a context and a list of habit events
     *
     * @param context
     * @param list
     */
    public MyFollowersAdapter(Context context, List<String> list) {
        mContext = context;
        entries = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView userRowName;
        private final TextView userScore;
        private final ImageView userProfile;

        private ViewHolder(View v, Context innerContext) {
            super(v);
            userRowName = v.findViewById(R.id.userRowName);
            userScore = v.findViewById(R.id.userRowScore);
            userProfile = v.findViewById(R.id.userRowProfileImage);
        }

    }

    @Override
    public MyFollowersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_user, parent, false);
        return (new ViewHolder(v, mContext));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String user = entries.get(position);
        ElasticSearch es = new ElasticSearch();
        User userAcc = es.getUser(user);

        holder.userRowName.setText(user);
        holder.userScore.setText(Double.toString(userAcc.getScore()));
        holder.userProfile.setImageBitmap(userAcc.getImage());
    }

    /**
     * Returns the count of followers in the follow list
     *
     * @return
     */
    @Override
    public int getItemCount() {
        Log.d("RV", "Item size [" + entries.size() + "]");
        return entries.size();
    }
}