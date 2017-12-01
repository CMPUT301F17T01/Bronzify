package cmput301f17t01.bronzify.adapters;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import cmput301f17t01.bronzify.controllers.ElasticSearch;
import cmput301f17t01.bronzify.models.HabitEvent;
import cmput301f17t01.bronzify.models.HabitType;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by kdehaan on 30/11/17.
 */

public class HabitTypeAdapter {
    private ElasticSearch elasticSearch = new ElasticSearch();
    private Boolean[] daysOfWeek;

    public HabitType read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
        HabitType type = new HabitType("tmp", "tmp", new Date(), daysOfWeek);
        String fieldname = null;
        reader.beginObject();

    /*    private User user;
        private String name;
        private String reason;
        private Date dateToStart;
        private Boolean[] daysOfWeek; // Sunday = 0, ... , Saturday = 6

        private ArrayList<HabitEvent> habitEvents = new ArrayList<>();
        private int numCompleted;
        private int numUncompleted;*/

        while (reader.hasNext()) {
            JsonToken token = reader.peek();
            if (token.equals(JsonToken.NAME)) {
                fieldname = reader.nextName();
            }
            if ("user".equals(fieldname)) {
                type.setUserID(reader.nextName());
            }
            if ("reason".equals(fieldname)) {
                type.setReason(reader.nextName());
            }
            if ("dateToStart".equals(fieldname)) {
                DateFormat format =
                        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);
                try {
                    Date date = format.parse(reader.nextName());
                    type.setDateToStart(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if ("daysOfWeek".equals(fieldname)) {
                type.setDaysOfWeek(boolArrayFromString(reader.nextName()));
            }
            if ("habitEvents".equals(fieldname)) {

            }

        }

        return type;
    }
    public void write(JsonWriter writer, HabitEvent event) throws IOException {
        if (event == null) {
            writer.nullValue();
            return;
        }
        writer.beginObject();
        writer.name("user");
        writer.value(event.getUser().getUserID());

        writer.endObject();
    }

    private Boolean[] boolArrayFromString(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        Boolean[] array = new Boolean[strings.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Boolean.parseBoolean(strings[i]);
        }
        return array;
    }
}
