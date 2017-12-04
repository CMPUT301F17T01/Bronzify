package cmput301f17t01.bronzify.adapters.recyclers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.activities.HabitEventActivity;
import cmput301f17t01.bronzify.activities.MyHistoryActivity;
import cmput301f17t01.bronzify.controllers.ElasticSearch;
import cmput301f17t01.bronzify.models.AppLocale;
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView userRowName;
        private TextView userScore;
        private ImageView userProfile;

        private ViewHolder(View v, Context innerContext) {
            super(v);
            v.setOnClickListener(this);
            userRowName = v.findViewById(R.id.userRowName);
            userScore = v.findViewById(R.id.userRowScore);
            userProfile = v.findViewById(R.id.userRowProfileImage);
        }

        @Override
        public void onClick(View v) {
            int pos = this.getAdapterPosition();
            String userSelected = entries.get(pos);

            Intent intent = new Intent(mContext, HabitEventActivity.class);

            //THIS IS THE NEW VERSION
            //AppLocale.getInstance().setUser(entries.get(pos));

            //OLD VERSION BELOW:
            //intent.putExtra("HABIT_TYPE", habitEvent.getHabitType());
            //intent.putExtra("GOAL_DATE", habitEvent.getGoalDate().getTime());

            mContext.startActivity(intent);
        }
    }

    @Override
    public MyFollowersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.event_row, parent, false);
        return (new ViewHolder(v, mContext));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String user = entries.get(position);
        ElasticSearch es = new ElasticSearch();
        User userAcc = es.getUser(user);

        holder.userRowName.setText(user);
        holder.userScore.setText(Double.toString(userAcc.getScore()));

        // TODO: THIS SHOULD WORK
        holder.userProfile.setImageBitmap(userAcc.getImage());
    }

    @Override
    public int getItemCount() {
        Log.d("RV", "Item size [" + entries.size() + "]");
        return entries.size();
    }
}