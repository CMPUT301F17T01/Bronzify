package cmput301f17t01.bronzify.adapters.recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.controllers.ListController;
import cmput301f17t01.bronzify.models.HabitType;

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
    private static ClickListener clickListener;



    public MyHabitAdapter(Context context, List<HabitType> list){
        entries = list;
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView habitTypeName;
        private TextView completion;
        private ImageButton imageButton;

        public ViewHolder(View v) {
            super(v);

            habitTypeName = (TextView) v.findViewById(R.id.habitTypeRow);
            completion = (TextView) v.findViewById(R.id.typeCompletion);
            imageButton = (ImageButton) v.findViewById(R.id.typeArrow);
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
        View v = LayoutInflater.from(mContext).inflate(R.layout.type_row,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HabitType ht = entries.get(position);
        holder.habitTypeName.setText(ht.getName());
        holder.completion.setText(String.valueOf(ht.getCompletionRatio())+"%");
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"Accepted : " + ht.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.d("RV", "Item size ["+entries.size()+"]");
        return entries.size();
    }

}