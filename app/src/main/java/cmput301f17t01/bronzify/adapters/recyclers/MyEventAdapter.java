package cmput301f17t01.bronzify.adapters.recyclers;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.activities.HabitEventActivity;

import cmput301f17t01.bronzify.adapters.HabitEventAdapter;

import cmput301f17t01.bronzify.activities.MyHistoryActivity;

import cmput301f17t01.bronzify.models.AppLocale;

import cmput301f17t01.bronzify.models.HabitEvent;

/*
 * Created by owenm_000 on 11/22/2017.
 */

public class MyEventAdapter extends RecyclerView.Adapter<MyEventAdapter.ViewHolder> {

    private Context mContext;
    private List<HabitEvent> entries;

    public MyEventAdapter(Context context, List<HabitEvent> list) {
        mContext = context;
        entries = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView habitEventName;
        private TextView habitEventDate;
        private ConstraintLayout constraintLayout;


        private ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            habitEventName = v.findViewById(R.id.habitEventRow);
            habitEventDate = v.findViewById(R.id.eventDate);
            constraintLayout = v.findViewById(R.id.event_row);
        }

        @Override
        public void onClick(View v) {
            int pos = this.getAdapterPosition();
            AppLocale.getInstance().setEvent(entries.get(pos));

            Intent intent = new Intent(mContext, HabitEventActivity.class);
            mContext.startActivity(intent);
        }
    }

    @Override
    public MyEventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.event_row, parent, false);
        return (new ViewHolder(v));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        HabitEvent habitEvent = entries.get(position);
        holder.habitEventName.setText(habitEvent.getHabitType());
        holder.habitEventDate.setText(habitEvent.goalDateToString());
        if (mContext instanceof MyHistoryActivity) {
            if (habitEvent.getCompleted() == Boolean.TRUE) {
                holder.constraintLayout.setBackgroundColor(0xFF00FF00);
            } else {
                holder.constraintLayout.setBackgroundColor(0xFFFF0000);
            }
        }
        else {
            // default color
            holder.constraintLayout.setBackgroundColor(0xFFEEEEEE);
        }
    }

    @Override
    public int getItemCount() {
        Log.d("RV", "Item size [" + entries.size() + "]");
        return entries.size();
    }
}