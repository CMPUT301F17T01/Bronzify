package cmput301f17t01.bronzify.adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
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
import cmput301f17t01.bronzify.models.HabitEvent;
import cmput301f17t01.bronzify.models.HabitType;

/*
 * Created by kdehaan on 30/11/17.
 */

public class HabitTypeAdapter extends TypeAdapter<HabitType> {
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);
    private final Gson gsonEvent = new GsonBuilder().registerTypeAdapter(HabitEvent.class,
            new HabitEventAdapter()).create();

    /**
     * Reads the habit event with a Json reader
     *
     * @param reader
     * @return
     * @throws IOException
     */
    public HabitType read(JsonReader reader) throws IOException {
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
                continue;
            }
            if ("user".equals(fieldname)) {
                type.setUserID(reader.nextString());
                continue;
            }
            if ("name".equals(fieldname)) {
                type.setName(reader.nextString());
            }
            if ("reason".equals(fieldname)) {
                type.setReason(reader.nextString());
                continue;
            }
            if ("dateToStart".equals(fieldname)) {
                try {
                    Date date = df.parse(reader.nextString());
                    type.setDateToStart(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if ("daysOfWeek".equals(fieldname)) {
                type.setDaysOfWeek(boolArrayFromString(reader.nextString()));
                continue;
            }
            if ("habitEvents".equals(fieldname)) {
                ArrayList<HabitEvent> events = gsonEvent.fromJson(reader.nextString(),
                        new TypeToken<ArrayList<HabitEvent>>(){}.getType());
                type.setHabitEvents(events);
                continue;
            }
            if ("numCompleted".equals(fieldname)) {
                type.setNumCompleted(Integer.valueOf(reader.nextString()));
                continue;
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
        writer.name("name");
        writer.value(type.getName());
        writer.name("reason");
        writer.value(type.getReason());
        writer.name("dateToStart");
        writer.value(df.format(type.getDateToStart()));
        writer.name("daysOfWeek");
        writer.value(Arrays.toString(type.getDaysOfWeek()));
        writer.name("habitEvents");
        writer.value(gsonEvent.toJson(type.getHabitEvents()));
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
    }
}
