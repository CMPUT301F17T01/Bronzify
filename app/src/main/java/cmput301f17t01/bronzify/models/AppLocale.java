package cmput301f17t01.bronzify.models;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by kdehaan on 11/11/17.
 */

public class AppLocale {
    private static final AppLocale ourInstance = new AppLocale();
    private User lastUser;
    private User user;
    private static final String FILENAME = "bronzify.sav";

    private ArrayList<User> savedUsers = new ArrayList<>();
    private ArrayList<HabitType> savedHabitTypes = new ArrayList<>();
    private ArrayList<HabitEvent> savedHabitEvents = new ArrayList<>();

    /**
     * Gets the instances of the app locale
     *
     * @return
     */
    public static AppLocale getInstance() {
        return ourInstance;
    }

    /**
     * Gets the last user that was accessing the app locale
     *
     * @return
     */
    public User getLastUser() {
        return lastUser;
    }


    /**
     * Method that retrieves the logged in user
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    public User getSavedUser(String userID) {
        //loadFromFile();
        Iterator<User> itr = savedUsers.iterator();
        while (itr.hasNext()) {
            User next = itr.next();
            if (next.getUserID() == userID) {
                return next;
            }
        }
        return null;
    }


    public void removeSavedUser(User deleteUser) {
        Iterator<User> itr = savedUsers.iterator();
        while (itr.hasNext()) {
            User next = itr.next();
            if (next.getUserID() == deleteUser.getUserID()) {
                savedUsers.remove(next);
                return;
            }
        }
    }

    public void saveUser(User newUser) {
        removeSavedUser(newUser);
        savedUsers.add(newUser);
        //saveInFile();
    }

    /**
     * Method that sets a user as the logged in user
     *
     * @param newUser
     */
    public void setUser(User newUser) {
        removeSavedUser(newUser);
        this.user = newUser;
        this.lastUser = newUser;
        saveUser(user);
    }

    public void logoutUser() {
        user = null;
    }


    /*private void loadFromFile() { //temporarily unimplemented
        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<User>>() {}.getType();
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            savedUsers = gson.fromJson(in, listType);
            Log.i("user0", savedUsers.get(0).toString());
        }
        catch (FileNotFoundException e) {
            savedUsers = new ArrayList<User>();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveInFile() { //temporarily unimplemented
        try {
            FileOutputStream fos = Context.getApplicationContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(savedUsers, writer);
            writer.flush();
            fos.close();
            Log.i("Saved", "in file");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    public ArrayList<HabitType> getSavedHabitTypes() {
        return savedHabitTypes;
    }

    public void setSavedHabitTypes(ArrayList<HabitType> savedHabitTypes) {
        this.savedHabitTypes = savedHabitTypes;
    }

    public ArrayList<HabitEvent> getSavedHabitEvents() {
        return savedHabitEvents;
    }

    public void setSavedHabitEvents(ArrayList<HabitEvent> savedHabitEvents) {
        this.savedHabitEvents = savedHabitEvents;
    }

    public ArrayList<User> getSavedUsers() {
        return savedUsers;
    }

    public void setSavedUsers(ArrayList<User> savedUsers) {
        this.savedUsers = savedUsers;
    }
}
