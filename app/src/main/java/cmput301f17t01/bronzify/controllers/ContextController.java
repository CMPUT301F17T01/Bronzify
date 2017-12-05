package cmput301f17t01.bronzify.controllers;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import cmput301f17t01.bronzify.adapters.UserAdapter;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by kdehaan on 02/12/17.
 */

public class ContextController {
    private final Context context;
    private final ElasticSearch elastic = new ElasticSearch();
    private final Gson userGson = new GsonBuilder().registerTypeAdapter(User.class,
            new UserAdapter()).create();

    private static final String FILENAME = "bronzify.sav";

    /**
     * Controls which context we are in
     *
     * @param context
     */
    public ContextController(Context context) {
        this.context = context;
    }

    /**
     * Updates which user is being saved as the local user, for offline capabilities. It will
     * save it in the file so that the user is able to log in if they are not connected
     * to the server
     *
     * @param currentUser
     */
    public void updateUser(User currentUser) {

        currentUser = elastic.update(currentUser);
        AppLocale appLocale = AppLocale.getInstance();
        appLocale.setUser(currentUser);
        saveInFile(appLocale.getLocalUsers());
    }

    public void updateUserFollowing(User currentUser) {
        User otherUser = elastic.getUser(currentUser.getUserID());
        currentUser.setPendingFollowRequests(otherUser.getPendingFollowRequests());
        currentUser.setFollowing(otherUser.getFollowing());
        currentUser.setPendingFollowRequests(otherUser.getPendingFollowRequests());
    }

    /**
     * This saves the user in the app local, for offline capabilities
     *
     * @param user
     */
    public void saveUser(User user) {
        AppLocale appLocale = AppLocale.getInstance();
        appLocale.addLocalUser(user);
        saveInFile(appLocale.getLocalUsers());
    }

    /**
     * Loads in the users from file and sends them to elastic search. Used to transfer from
     * the device to the elastic search server
     *
     * @return
     */
    public ArrayList<User> loadFromFile() {
        try {
            Type listType = new TypeToken<ArrayList<User>>() {
            }.getType();
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Log.i("Loaded", "from file");
            ArrayList<User> usersArray = userGson.fromJson(in, listType);
            if (usersArray == null) {
                return new ArrayList<>();
            } else {
                return usersArray;
            }

        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Saves the users into the file on the local device
     *
     * @param savedUsers
     */
    public void saveInFile(ArrayList<User> savedUsers) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            userGson.toJson(savedUsers, writer);
            writer.flush();
            fos.close();
            Log.i("Saved", "in file");

        } catch (FileNotFoundException e) {
            Log.i("File Error", "File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            Log.i("Null context", "call appLocale.setContext(getApplicationContext()) first");
            e.printStackTrace();
        }

    }


}
