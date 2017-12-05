package cmput301f17t01.bronzify.adapters.recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.controllers.ElasticSearch;
import cmput301f17t01.bronzify.models.HabitType;

/*
 * Created by owenm_000 on 11/22/2017.
 */

public class MyFeedAdapter extends RecyclerView.Adapter<MyFeedAdapter.ViewHolder> {

    private Context mContext;
    private List<HabitType> entries;

    public MyFeedAdapter(Context context, List<HabitType> list) {
        mContext = context;
        entries = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView userRowName;
        private TextView typeRowName;
        private TextView typeCompletion;
        private ImageView userProfile;

        private ViewHolder(View v, Context innerContext) {
            super(v);
            userRowName = v.findViewById(R.id.feedRowUserName);
            typeRowName = v.findViewById(R.id.feedRowTypeName);
            userProfile = v.findViewById(R.id.feedRowProfileImage);
            typeCompletion = v.findViewById(R.id.feedTypeCompletion);
        }

    }

    @Override
    public MyFeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_feed, parent, false);
        return (new ViewHolder(v, mContext));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        HabitType type = entries.get(position);
        ElasticSearch es = new ElasticSearch();

        holder.userRowName.setText(type.getUserID());
        holder.typeRowName.setText(type.getName());
        holder.typeCompletion.setText(String.valueOf(type.getCompletionRatio()));
        holder.userProfile.setImageBitmap(es.getUser(type.getUserID()).getImage());
    }

    @Override
    public int getItemCount() {
        Log.d("RV", "Item size [" + entries.size() + "]");
        return entries.size();
    }

    public void setFilter(ArrayList<HabitType> newEvents){
        entries = new ArrayList<HabitType>();
        entries.addAll(newEvents);
        notifyDataSetChanged();

    }
}