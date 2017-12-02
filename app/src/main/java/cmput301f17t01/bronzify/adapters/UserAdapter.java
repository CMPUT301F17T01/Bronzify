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
import cmput301f17t01.bronzify.models.User;

/**
 * Created by kdehaan on 01/12/17.
 */



public class UserAdapter extends TypeAdapter<User> {
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);
    private final Gson gsonType = new GsonBuilder().registerTypeAdapter(HabitType.class,
            new HabitTypeAdapter()).create();

    public User read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
//        Boolean[] daysOfWeek = {false, false, false, false, false, false, false,};
//        HabitType type = new HabitType("tmp", "tmp", new Date(), daysOfWeek);
        User user = new User("userID");
        String fieldname = null;
        reader.beginObject();


        while (reader.hasNext()) {
            JsonToken token = reader.peek();
            if (token.equals(JsonToken.NAME)) {
                fieldname = reader.nextName();
                continue;
            }
            if ("userID".equals(fieldname)) {
                user.setUserID(reader.nextString());
                continue;
            }
            if ("dateCreated".equals(fieldname)) {
                try {
                    Date date = df.parse(reader.nextString());
                    user.setDateCreated(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if ("lastUpdated".equals(fieldname)) {
                try {
                    Date date = df.parse(reader.nextString());
                    user.setLastUpdated(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if ("lastInfluenced".equals(fieldname)) {
                try {
                    Date date = df.parse(reader.nextString());
                    user.setLastInfluenced(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if ("habitTypes".equals(fieldname)) {
                ArrayList<HabitType> types = gsonType.fromJson(reader.nextString(),
                        new TypeToken<ArrayList<HabitType>>(){}.getType());
                user.setHabitTypes(types);
                continue;
            }
            if ("following".equals(fieldname)) {
                ArrayList<String> following = gsonType.fromJson(reader.nextString(),
                        new TypeToken<ArrayList<String>>(){}.getType());
                user.setFollowing(following);
                continue;
            }
            if ("pendingFollowRequests".equals(fieldname)) {
                ArrayList<String> pendingFollows = gsonType.fromJson(reader.nextString(),
                        new TypeToken<ArrayList<String>>(){}.getType());
                user.setPendingFollowRequests(pendingFollows);
                continue;
            }
            if ("faithfulness".equals(fieldname)) {
                //todo: implement
            }

        }
        reader.endObject();

        return user;
    }
    public void write(JsonWriter writer, User user) throws IOException {
        if (user == null) {
            writer.nullValue();
            return;
        }
        writer.beginObject();
        writer.name("userID");
        writer.value(user.getUserID());
        writer.name("dateCreated");
        writer.value(df.format(user.getDateCreated()));
        writer.name("lastUpdated");
        writer.value(df.format(user.getLastUpdated()));
        writer.name("lastInfluenced");
        writer.value(df.format(user.getLastInfluenced()));
        writer.name("habitTypes");
        writer.value(gsonType.toJson(user.getHabitTypes()));
        writer.name("following");
        writer.value(gsonType.toJson(user.getFollowing()));
        writer.name("pendingFollowRequests");
        writer.value(gsonType.toJson(user.getPendingFollowRequests()));
        writer.endObject();
    }

}
