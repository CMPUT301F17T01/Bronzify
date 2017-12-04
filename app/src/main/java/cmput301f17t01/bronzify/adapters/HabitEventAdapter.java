package cmput301f17t01.bronzify.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cmput301f17t01.bronzify.controllers.ElasticSearch;
import cmput301f17t01.bronzify.models.HabitEvent;

/**
 * Created by kdehaan on 30/11/17.
 */


public class HabitEventAdapter extends TypeAdapter<HabitEvent> {
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);

    public HabitEvent read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
        HabitEvent event = new HabitEvent(new Date(), "placeholder");
        String fieldname = null;
        reader.beginObject();
        while (reader.hasNext()) {
            JsonToken token = reader.peek();
            if (token.equals(JsonToken.NAME)) {
                fieldname = reader.nextName();
                continue;
            }
            if ("user".equals(fieldname)) {
                event.setUserID(reader.nextString());
                continue;
            }
            if ("goalDate".equals(fieldname)) {
                try {
                    Date date = df.parse(reader.nextString());
                    event.setGoalDate(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                continue;

            }
            if ("completedDate".equals(fieldname)) {
                DateFormat format =
                        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);
                try {
                    Date date = format.parse(reader.nextString());
                    event.setCompletedDate(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if ("comment".equals(fieldname)) {
                event.setComment(reader.nextString());
                continue;
            }
            if ("completed".equals(fieldname)) {
                event.setCompleted(Boolean.valueOf(reader.nextString()));
                continue;
            }
            if ("image".equals(fieldname)) {
                continue; //TODO
            }
            if ("location".equals(fieldname)) {
                continue; //TODO
            }
            if ("habitType".equals(fieldname)) {
                event.setHabitType(reader.nextString());
            }
        }
        reader.endObject();

        return event;
    }
    public void write(JsonWriter writer, HabitEvent event) throws IOException {
        if (event == null) {
            writer.nullValue();
            return;
        }
        writer.beginObject();
        writer.name("user");
        writer.value(event.getUserID());
        writer.name("goalDate");
        writer.value(df.format(event.getGoalDate()));
        writer.name("completedDate");
        try {
            writer.value(df.format(event.getCompletedDate()));
        } catch (NullPointerException e) {
            writer.nullValue();
        }
        writer.name("comment");
        writer.value(event.getComment());
        writer.name("completed");
        writer.value(event.getCompleted().toString());
        writer.name("image");
        try{
            writer.value(event.getImage().toString());
        } catch (NullPointerException e) {
            writer.nullValue();
        }
        writer.name("location");
        try{
            writer.value(event.getLocation().toString());
        } catch (NullPointerException e) {
            writer.nullValue();
        }
        writer.name("habitType");
        writer.value(event.getHabitType());
        writer.endObject();
    }
}