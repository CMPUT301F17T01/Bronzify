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
    private HabitEvent event;
    private HabitType type;
//    String path = Environment.getFiles().getAbsolutePath();
//    private Context context = null;
//    private static final String FILENAME = "bronzify.sav";

//    private static final Gson userGson = new GsonBuilder().registerTypeAdapter(User.class,
//            new UserAdapter()).create();

    private ArrayList<User> localUsers = new ArrayList<>();
    private ArrayList<HabitType> savedHabitTypes = new ArrayList<>();
    private ArrayList<HabitEvent> savedHabitEvents = new ArrayList<>();

    /**
     * Gets the instances of the app locale
     *
     * @return ourInstance
     */
    public static AppLocale getInstance() {
        return ourInstance;
    }

    /**
     * Gets the last user that was accessing the app locale
     *
     * @return lastUser
     */
    public User getLastUser() {
        return lastUser;
    }


    /**
     * Method that retrieves the logged in user
     *
     * @return user
     */
    public User getUser() {
        return user;
    }


    public User getLocalUser(String userID) {
        if (localUsers == null) {
            return null;
        }
        Iterator<User> itr = localUsers.iterator();

        while (itr.hasNext()) {
            User next = itr.next();
            if (next.getUserID().equals(userID)) {
                return next;
            }
        }
        return null;
    }


    public void removeLocalUser(User deleteUser) {
        Iterator<User> itr = localUsers.iterator();
        while (itr.hasNext()) {
            User next = itr.next();
            if (next.getUserID().equals(deleteUser.getUserID())) {
                localUsers.remove(next);
                return;
            }
        }
    }

    public void removeLocalUser(String userID) {
        Iterator<User> itr = localUsers.iterator();
        while (itr.hasNext()) {
            User next = itr.next();
            if (next.getUserID().equals(userID)) {
                localUsers.remove(next);
                return;
            }
        }
    }

    public void addLocalUser(User newUser) {
        if (!localUsers.contains(newUser)) {
            localUsers.add(newUser);
        }

    }

    /**
     * Method that sets a user as the logged in user
     *
     * newUser @param
     */
    public void setUser(User newUser) {
        this.user = newUser;
        this.lastUser = newUser;
        addLocalUser(newUser);
    }

    public void logoutUser() {
        user = null;
    }



//    private void loadFromFile() { //temporarily unimplemented
////        try {
////            Type listType = new TypeToken<ArrayList<User>>() {}.getType();
////            FileInputStream fis = context.openFileInput(FILENAME);
////            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
////            savedUsers = userGson.fromJson(in, listType);
////            Log.i("user0", savedUsers.get(0).toString());
////
////        } catch (FileNotFoundException e) {
////            savedUsers = new ArrayList<User>();
////        }
//    }
//
//
//    private void saveInFile() { //temporarily unimplemented
////        try {
////            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
////            OutputStreamWriter writer = new OutputStreamWriter(fos);
////            userGson.toJson(savedUsers, writer);
////            writer.flush();
////            fos.close();
////            Log.i("Saved", "in file");
////
////        } catch (FileNotFoundException e) {
////            Log.i("File Error", "File not found");
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        } catch (NullPointerException e) {
////            Log.i("Null context", "call appLocale.setContext(getApplicationContext()) first");
////            e.printStackTrace();
////        }
//
//    }


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

    public ArrayList<User> getLocalUsers() {
        return localUsers;
    }

    public void setLocalUsers(ArrayList<User> localUsers) {
        this.localUsers = localUsers;
    }

//    public Context getContext() {
//        return context;
//    }
//
//    public void setContext(Context context) {
//        this.context = context;
//    }

    public HabitEvent getEvent() {
        return event;
    }

    public void setEvent(HabitEvent event) {
        this.event = event;
    }

    public HabitType getType() {
        return type;
    }

    public void setType(HabitType type) {
        this.type = type;
    }
}
