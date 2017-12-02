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
import cmput301f17t01.bronzify.controllers.ListController;
import cmput301f17t01.bronzify.models.HabitType;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by owenm_000 on 11/22/2017.
 */

public class MyHabitAdapter extends RecyclerView.Adapter<MyHabitAdapter.ViewHolder> {
    private static final String TAG = "MyHabitAdapter";

    // TODO: SHOULD THIS BE USERS LIST OR JUST STRINGS OF USERID'S?
    // THOUGHTS?

    private List<HabitType> entries;
    private Context mContext;
    private static ListController controller;
    private TextView habitTypeName;
    private TextView completion;

    public MyHabitAdapter(Context context, List<HabitType> list){
        entries = list;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
            habitTypeName = (TextView) v.findViewById(R.id.typeCompletion);
            completion = (TextView) v.findViewById(R.id.acceptFollow);

        }
        public TextView getHabitTypeName() {
            return habitTypeName;
        }

        public TextView getCompletion() {
            return completion;
        }


    }

    @Override
    public MyHabitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_follow,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        HabitType u = entries.get(position);

        /*holder.rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the clicked item label
                String userName = entries.get(position).getUserID();

                entries.remove(position);


                notifyItemRemoved(position);


                notifyItemRangeChanged(position,entries.size());

                Toast.makeText(mContext,"Rejected : " + userName, Toast.LENGTH_SHORT).show();
            }
        });*/

       /* holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the clicked item label
                String userName = entries.get(position).getUserID();
                // TODO: ADD THE USER TO THE FOLLOWERS LIST
                entries.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,entries.size());

                Toast.makeText(mContext,"Accepted : " + userName, Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        Log.d("RV", "Item size ["+entries.size()+"]");
        return entries.size();
    }
}