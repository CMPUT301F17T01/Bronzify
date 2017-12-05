package cmput301f17t01.bronzify.adapters;

import android.location.Location;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;


/**
 * Created by kdehaan on 04/12/17.
 */

class LocationAdapter extends TypeAdapter<Location> {

    public Location read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
//        Boolean[] daysOfWeek = {false, false, false, false, false, false, false,};
//        HabitType type = new HabitType("tmp", "tmp", new Date(), daysOfWeek);
        Location loc = new Location("STORAGE");
        String fieldname = null;
        reader.beginObject();


        while (reader.hasNext()) {
            JsonToken token = reader.peek();
            if (token.equals(JsonToken.NAME)) {
                fieldname = reader.nextName();
                continue;
            }
            if ("lat".equals(fieldname)) {
                loc.setLatitude(reader.nextDouble());
                continue;
            }
            if ("lon".equals(fieldname)) {
                loc.setLongitude(reader.nextDouble());
                continue;
            }


        }
        reader.endObject();

        return loc;
    }

    public void write(JsonWriter writer, Location loc) throws IOException {
        if (loc == null) {
            writer.nullValue();
            return;
        }
        writer.beginObject();
        writer.name("lat");
        writer.value(loc.getLatitude());
        writer.name("lon");
        writer.value(loc.getLongitude());
        writer.endObject();
    }
}
