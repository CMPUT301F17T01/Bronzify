package cmput301f17t01.bronzify.adapters.recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.controllers.ElasticSearch;
import cmput301f17t01.bronzify.controllers.ListController;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by owenm_000 on 11/22/2017.
 */

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.ViewHolder> {
    private static final String TAG = "FollowAdapter";

    // TODO: SHOULD THIS BE USERS LIST OR JUST STRINGS OF USERID'S?
    // THOUGHTS?

    private List<String> entries;
    private Context mContext;
    private static ListController controller;

    public FollowAdapter(Context context, List<String> list ){
        entries = list;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView followReqText;
        public ImageButton acceptButton;
        public ImageButton rejectButton;
        public RelativeLayout followRow;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            /*v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Object clicked = entries.get(getAdapterPosition());
                    controller.onClick(clicked);
                    notifyDataSetChanged();

                }
            });*/
            followReqText = v.findViewById(R.id.followReqName);  //Gotta add these in your xml, they are absent
            acceptButton = v.findViewById(R.id.acceptFollow);
            rejectButton = v.findViewById(R.id.rejectFollow);
            followRow = v.findViewById(R.id.rowFollow);    //Gotta add these in your xml, they are absent

        }
        public TextView getTextView() {
            return followReqText;
        }
    }

    @Override
    public FollowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_follow,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final String u = entries.get(position);
        holder.followReqText.setText(u);

        holder.rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the clicked item label
                entries.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,entries.size());
                Toast.makeText(mContext,"Rejected : " + u, Toast.LENGTH_SHORT).show();
            }
        });

        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the clicked item label
                String userName = entries.get(position);
                // TODO: ADD THE USER TO THE FOLLOWERS LIST
                entries.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,entries.size());
                User user = AppLocale.getInstance().getUser();
                new ElasticSearch().acceptFollow(user,userName);
                Toast.makeText(mContext,"Accepted : " + u, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("RV", "Item size ["+entries.size()+"]");
        return entries.size();
    }
}