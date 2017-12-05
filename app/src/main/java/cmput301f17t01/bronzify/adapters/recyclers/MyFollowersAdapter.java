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

/*
 * Created by owenm_000 on 11/22/2017.
 */

public class MyFollowersAdapter extends RecyclerView.Adapter<MyFollowersAdapter.ViewHolder> {

    private Context mContext;
    private List<String> entries;

    public MyFollowersAdapter(Context context, List<String> list) {
        mContext = context;
        entries = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView userRowName;
        private TextView userScore;
        private ImageView userProfile;

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

    @Override
    public int getItemCount() {
        Log.d("RV", "Item size [" + entries.size() + "]");
        return entries.size();
    }
}