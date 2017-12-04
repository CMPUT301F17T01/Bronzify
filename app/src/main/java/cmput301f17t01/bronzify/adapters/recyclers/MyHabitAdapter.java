package cmput301f17t01.bronzify.adapters.recyclers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.activities.HabitTypeActivity;
import cmput301f17t01.bronzify.models.HabitType;

/**
 * Created by owenm_000 on 11/22/2017.
 */

public class MyHabitAdapter extends RecyclerView.Adapter<MyHabitAdapter.ViewHolder> {

    private Context mContext;
    private List<HabitType> entries;

    public MyHabitAdapter(Context context, List<HabitType> list) {
        mContext = context;
        entries = list;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView habitTypeName;
        private TextView completion;
        private ImageButton imageButton;

        private ViewHolder(View v) {
            super(v);
            habitTypeName = v.findViewById(R.id.habitTypeRow);
            completion = v.findViewById(R.id.typeCompletion);
            imageButton = v.findViewById(R.id.typeArrow);

            v.setOnClickListener(this);
            //habitTypeName = v.findViewById(R.id.habitEventRow);
            //completion = v.findViewById(R.id.eventDate);
        }

        @Override
        public void onClick(View v) {
            int pos = this.getAdapterPosition(); // Position of Habit Type clicked
            Intent intent = new Intent(mContext, HabitTypeActivity.class);
            intent.putExtra("SELECTED_HABIT", pos); // Get should work since position should always be same
            mContext.startActivity(intent);
        }
    }

    @Override
    public MyHabitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.type_row, parent, false);
        return (new ViewHolder(v));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        HabitType habitType = entries.get(position);
        holder.habitTypeName.setText(habitType.getName());
        String completionString = Integer.toString(habitType.getCompletionRatio()) + "%";
        holder.completion.setText(completionString);
    }

    @Override
    public int getItemCount() {
        Log.d("RV", "Item size [" + entries.size() + "]");
        return entries.size();
    }
}