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

/*private User user;
private Date goalDate; // Date for habit event to happen
private Date completedDate;
private String comment; // Max 20 Char
private Boolean completed;
private Bitmap image; // Change?
private GoogleMap location; // Unsure of location type

private String habitType;

// Constructor
public HabitEvent(Date goalDate, String habitType){
        this.user = AppLocale.getInstance().getUser();
        this.goalDate = goalDate;
        this.comment = ""; // Default comment is blank
        this.completed = false;
        this.image = null;
        this.location = null;
        this.habitType = habitType;
        }*/

public class HabitEventAdapter extends TypeAdapter<HabitEvent> {
    private ElasticSearch elasticSearch = new ElasticSearch();

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
            }
            if ("user".equals(fieldname)) {
                event.setUserID(reader.nextName());
            }
            if ("goalDate".equals(fieldname)) {
                DateFormat format =
                        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);
                try {
                    Date date = format.parse(reader.nextName());
                    event.setGoalDate(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            if ("completedDate".equals(fieldname)) {
                DateFormat format =
                        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);
                try {
                    Date date = format.parse(reader.nextName());
                    event.setCompletedDate(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if ("comment".equals(fieldname)) {
                event.setComment(reader.nextName());
            }
            if ("completed".equals(fieldname)) {
                event.setCompleted(Boolean.valueOf(reader.nextName()));
            }
            if ("image".equals(fieldname)) {
                continue; //TODO
            }
            if ("location".equals(fieldname)) {
                continue; //TODO
            }
            if ("habitType".equals(fieldname)) {
                event.setHabitType(reader.nextName());
            }
        }

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
        writer.value(event.getGoalDate().toString());
        writer.name("completedDate");
        writer.value(event.getCompletedDate().toString());
        writer.name("comment");
        writer.value(event.getComment());
        writer.name("completed");
        writer.value(event.getCompleted().toString());
        writer.name("image");
        writer.value(event.getImage().toString());
        writer.name("location");
        writer.value(event.getLocation().toString());
        writer.name("habitType");
        writer.value(event.getHabitType());
        writer.endObject();
    }
}