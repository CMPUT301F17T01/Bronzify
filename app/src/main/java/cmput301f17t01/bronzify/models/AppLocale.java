package cmput301f17t01.bronzify.models;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kdehaan on 11/11/17.
 */

public class AppLocale {
    private static final AppLocale ourInstance = new AppLocale();
    private User lastUser;
    private User loggedInUser;
    private static final String FILENAME = "bronzify.sav";

    private ArrayList<User> savedUsers;


    public static AppLocale getInstance() {
        return ourInstance;
    }

    private AppLocale() {
    }

    public User getLastUser() {
        return lastUser;
    }

    public User getUser() {
        return loggedInUser;
    }

    public User getUser(String userID) {
        Iterator<User> itr = savedUsers.iterator();
        while (itr.hasNext()) {
            User next = itr.next();
            if (next.getUserID() == userID) {
                return next;
            }
        }
        return null;
    }

    public void setUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        this.lastUser = loggedInUser;
    }

    public void logoutUser() {
        loggedInUser = null;
    }

    private void loadFromFile() {
        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<User>>() {}.getType();
            FileReader fstream = new FileReader(FILENAME);
            BufferedReader in = new BufferedReader(fstream);
            
            String usersGson = in.readLine();
            Log.i("read", usersGson);


        } catch (FileNotFoundException e) {
            savedUsers = new ArrayList<User>();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveInFile() {
        try {
            Gson gson = new Gson();
            String usersGson = gson.toJson(savedUsers);
            FileWriter fstream = new FileWriter(FILENAME);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(usersGson);
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
