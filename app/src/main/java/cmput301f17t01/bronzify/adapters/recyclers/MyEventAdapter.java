package cmput301f17t01.bronzify.adapters.recyclers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.activities.HabitEventActivity;
import cmput301f17t01.bronzify.adapters.HabitEventAdapter;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.HabitEvent;

/**
 * Created by owenm_000 on 11/22/2017.
 */

public class MyEventAdapter extends RecyclerView.Adapter<MyEventAdapter.ViewHolder> {

    private Context mContext;
    private List<HabitEvent> entries;

    public MyEventAdapter(Context context, List<HabitEvent> list){
        mContext = context;
        entries = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView habitEventName;
        private TextView habitEventDate;

        private ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            habitEventName = v.findViewById(R.id.habitEventRow);
            habitEventDate = v.findViewById(R.id.eventDate);
        }

        @Override
        public void onClick(View v) {
            int pos = this.getAdapterPosition(); // Position of Habit Type clicked
            Intent intent = new Intent(mContext, HabitEventActivity.class);
            AppLocale.getInstance().setEvent(entries.get(pos));
//            Gson gsonEvent = new GsonBuilder().registerTypeAdapter(HabitEvent.class,
//                    new HabitEventAdapter()).create();
//            String event = gsonEvent.toJson(entries.get(pos));
//            intent.putExtra("SELECTED_HABIT", event); // Get should work since position should always be same
            mContext.startActivity(intent);
        }
    }

    @Override
    public MyEventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.event_row,parent,false);
        return (new ViewHolder(v));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        HabitEvent habitEvent = entries.get(position);
        holder.habitEventName.setText(habitEvent.getHabitType());
        holder.habitEventDate.setText(habitEvent.goalDateToString());
    }

    @Override
    public int getItemCount() {
        Log.d("RV", "Item size ["+entries.size()+"]");
        return entries.size();
    }
}