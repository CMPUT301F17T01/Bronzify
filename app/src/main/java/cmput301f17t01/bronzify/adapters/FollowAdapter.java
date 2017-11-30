package cmput301f17t01.bronzify.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.List;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.controllers.ListController;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by owenm_000 on 11/22/2017.
 */

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.ViewHolder> {
    private static final String TAG = "ListAdapter";
    private List<User> entries;
    private static ListController controller;

    public AnimalsAdapter(Context context,List<String> list){
        mDataSet = list;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView followReqText;
        public ImageButton acceptButton;
        public ImageButton rejectButton;

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
            followReqText = (TextView) v.findViewById(R.id.followRequest);
            acceptButton = (ImageButton) v.findViewById(R.id.accept);
            rejectButton = (ImageButton) v.findViewById(R.id.reject);

        }
        public TextView getTextView() {
            return followReqText;
        }

    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.followRow,parent,false);
        return new ViewHolder(View v);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        Log.d("RV", "Item size ["+entries.size()+"]");
        return entries.size();

    }
}