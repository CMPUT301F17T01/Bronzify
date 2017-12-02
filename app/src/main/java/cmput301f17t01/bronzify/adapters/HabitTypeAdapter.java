package cmput301f17t01.bronzify.adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
                DateFormat format =
                        new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                try {
                    Date date = format.parse(reader.nextString());
                    event.setGoalDate(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                continue;

            }
            if ("completedDate".equals(fieldname)) {
                DateFormat format =
                        new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
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
        writer.value(event.getGoalDate().toString());
        writer.name("completedDate");
        try {
            writer.value(event.getCompletedDate().toString());
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
//    private final Gson gsonEvent = new GsonBuilder().registerTypeAdapter(HabitEvent.class,
//            new HabitEventAdapter()).create();

   /* public HabitType read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
        Boolean[] daysOfWeek = {false, false, false, false, false, false, false,};
        HabitType type = new HabitType("tmp", "tmp", new Date(), daysOfWeek);
        String fieldname = null;
        reader.beginObject();


        while (reader.hasNext()) {
            JsonToken token = reader.peek();
            if (token.equals(JsonToken.NAME)) {
                fieldname = reader.nextName();
            }
            if ("user".equals(fieldname)) {
                type.setUserID(reader.nextString());
            }
            if ("reason".equals(fieldname)) {
                type.setReason(reader.nextString());
            }
            if ("dateToStart".equals(fieldname)) {
                DateFormat format =
                        new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                try {
                    Date date = format.parse(reader.nextString());
                    type.setDateToStart(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if ("daysOfWeek".equals(fieldname)) {
                type.setDaysOfWeek(boolArrayFromString(reader.nextString()));
            }
//            if ("habitEvents".equals(fieldname)) {
//                ArrayList<HabitEvent> events = gsonEvent.fromJson(reader.nextString(),
//                        new TypeToken<ArrayList<HabitEvent>>(){}.getType());
//                type.setHabitEvents(events);
//            }
            if ("numCompleted".equals(fieldname)) {
                type.setNumCompleted(Integer.valueOf(reader.nextString()));
            }
            if ("numUncompleted".equals(fieldname)) {
                type.setNumUncompleted(Integer.valueOf(reader.nextString()));
            }

        }
        reader.endObject();

        return type;
    }
    public void write(JsonWriter writer, HabitType type) throws IOException {
        if (type == null) {
            writer.nullValue();
            return;
        }
        writer.beginObject();
        writer.name("user");
        writer.value(type.getUserID());
        writer.name("reason");
        writer.value(type.getReason());
        writer.name("dateToStart");
        writer.value(type.getDateToStart().toString());
        writer.name("daysOfWeek");
        writer.value(Arrays.toString(type.getDaysOfWeek()));
//        writer.name("habitEvents");
//        writer.value(gsonEvent.toJson(type.getHabitEvents()));
        writer.name("numCompleted");
        writer.value(type.getNumCompleted());
        writer.name("numUncompleted");
        writer.value(type.getNumUncompleted());
        writer.endObject();
    }

    private Boolean[] boolArrayFromString(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        Boolean[] array = new Boolean[strings.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Boolean.parseBoolean(strings[i]);
        }
        return array;
    }*/
}
