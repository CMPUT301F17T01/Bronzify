package cmput301f17t01.bronzify.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import cmput301f17t01.bronzify.models.HabitEvent;

/*
 * Created by kdehaan on 30/11/17.
 */

public class HabitEventAdapter extends TypeAdapter<HabitEvent> {
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);
    private final Gson gsonLoc = new GsonBuilder().registerTypeAdapter(Location.class,
            new LocationAdapter()).create();

    /**
     * Reads the habit event with a Json reader
     *
     * @param reader
     * @return
     * @throws IOException
     */
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
//            if ("completedDate".equals(fieldname)) {
//                DateFormat format =
//                        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);
//                try {
//                    Date date = format.parse(reader.nextString());
//                    event.setCompletedDate(date);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                continue;
//            }
            if ("comment".equals(fieldname)) {
                event.setComment(reader.nextString());
                continue;
            }
            if ("completed".equals(fieldname)) {
                try{
                    event.setCompleted(Boolean.valueOf(reader.nextString()));
                } catch (NullPointerException e){
                    event.setCompleted(null);
                }

                continue;
            }
            if ("image".equals(fieldname)) {
                event.setImage(getBitmapFromString(reader.nextString()));
                continue;
            }
            if ("location".equals(fieldname)) {
                event.setLocation(gsonLoc.fromJson(reader.nextString(), Location.class));
                continue;
            }
            if ("habitType".equals(fieldname)) {
                event.setHabitType(reader.nextString());
            }
        }
        reader.endObject();

        return event;
    }

    /**
     * Write to the habit event with a Json writer
     *
     * @param writer
     * @param event
     * @throws IOException
     */
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
//        writer.name("completedDate");
//        try {
//            writer.value(df.format(event.getCompletedDate()));
//        } catch (NullPointerException e) {
//            writer.nullValue();
//        }
        writer.name("comment");
        writer.value(event.getComment());
        writer.name("completed");
        try{
            writer.value(event.getCompleted().toString());
        } catch (NullPointerException e) {
            writer.nullValue();
        }
        writer.name("image");
        try{
            writer.value(getStringFromBitmap(event.getImage()));
        } catch (NullPointerException e) {
            writer.nullValue();
        }
        writer.name("location");
        try{
            writer.value(gsonLoc.toJson(event.getLocation()));
        } catch (NullPointerException e) {
            writer.nullValue();
        }
        writer.name("habitType");
        writer.value(event.getHabitType());
        writer.endObject();
    }

    /**
     * Returns the string converting from the bitmap
     *
     * @param bitmapPicture
     * @return
     */
    private String getStringFromBitmap(Bitmap bitmapPicture) {
        final int COMPRESSION_QUALITY = 100;
        String encodedImage;
        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
        bitmapPicture.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY,
                byteArrayBitmapStream);
        byte[] b = byteArrayBitmapStream.toByteArray();
        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }

    /**
     * Returns the bitmap converting from the string
     *
     * @param stringPicture
     * @return
     */
    private Bitmap getBitmapFromString(String stringPicture) {
        byte[] decodedString = Base64.decode(stringPicture, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}