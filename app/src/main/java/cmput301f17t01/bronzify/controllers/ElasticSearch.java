package cmput301f17t01.bronzify.controllers;


import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;


import java.io.IOException;
import java.util.Date;

import cmput301f17t01.bronzify.adapters.UserAdapter;
import cmput301f17t01.bronzify.exceptions.ElasticException;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.User;
import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;

/**
 * Created by kdehaan on 10/11/17.
 */



public class ElasticSearch {
    private static JestDroidClient client;
//    private static String serverString = "http://cmput301.softwareprocess.es:8080";
    private static String serverString = "http://localhost:9200";
    private static String indexString = "cmput301f17t01_bronzify";
    private static String typeString = "test_user_v2";
    private static final Gson userGson = new GsonBuilder().registerTypeAdapter(User.class,
            new UserAdapter()).create();



    public User update(User user) {
        User remoteUser = getUser(user.getUserID());
        if (remoteUser == null) { //elasticsearch error
            return user;
        }
        if (remoteUser.getLastUpdated().after(user.getLastUpdated())) {
            return remoteUser;
        } else {
            user.setLastUpdated(new Date());
            postUser(user);
            return user;
        }
    }

    public void requestFollow(User user, String otherUserID) {
        if (user.getFollowing().contains(otherUserID)){
            Log.i("Error", "already following");
            return;
        }
        User remoteUser = getUser(otherUserID);
        if (remoteUser == null) {
            Log.i("Error", "user not found");
            return;
        }
        if (remoteUser.getPendingFollowRequests().contains(user.getUserID())) {
            Log.i("Error", "already sent request");
            return;
        }
        remoteUser.addPendingFollowRequest(user.getUserID());
        remoteUser.setLastInfluenced(new Date());
        postUser(remoteUser);
    }

    public void acceptFollow(User user, String otherUserID) {
        User remoteUser = getUser(otherUserID);
        remoteUser.addFollowing(user.getUserID());
        remoteUser.setLastInfluenced(new Date());
        postUser(remoteUser);
        user.removePendingFollowRequest(otherUserID);
        userUpdate(user);
    }

    public void userUpdate(User user) {
        ElasticSearch elastic = new ElasticSearch();
        User newestUser = elastic.update(user);

        user.setLastUpdated(newestUser.getLastUpdated());
        user.setFollowing(newestUser.getFollowing());
        user.setPendingFollowRequests(newestUser.getPendingFollowRequests());
        user.setHabitTypes(newestUser.getHabitTypes());
        AppLocale.getInstance().setUser(user);
    }

    public void postUser(User user) {
        ElasticSearch.PostUser addUserTask
                = new ElasticSearch.PostUser();
        addUserTask.execute(user);
    }

    public User getUserLocalFirst(String userID) {
        User foundUser = AppLocale.getInstance().getLocalUser(userID);
        if (foundUser == null) {
            ElasticSearch.GetUser getUserTask
                    = new ElasticSearch.GetUser();
            getUserTask.execute(userID);
            try {
                foundUser = getUserTask.get();
            } catch (Exception e) {
                foundUser = null;
//            e.printStackTrace();
            }
        }
        return foundUser;
    }

    public User getUser(String userID) {
        User foundUser;
        ElasticSearch.GetUser getUserTask
                = new ElasticSearch.GetUser();
        getUserTask.execute(userID);
        try {
            foundUser = getUserTask.get();
        } catch (Exception e) {
            foundUser = null;
//            e.printStackTrace();
        }
        if (foundUser == null) {
            foundUser = AppLocale.getInstance().getLocalUser(userID);
        }

        return foundUser;
    }

    public void deleteUser(String userID) {
        ElasticSearch.DeleteUser deleteUserTask
                = new ElasticSearch.DeleteUser();
        deleteUserTask.execute(userID);
    }


    public static class PostUser extends AsyncTask<User, Void, Void> {
        @Override
        protected Void doInBackground(User... users) {
            verifySettings();
            for (User user : users) {
                String userJson = userGson.toJson(user);
                Index index = new Index.Builder(userJson)
                        .index(indexString)
                        .type(typeString)
                        .id(user.getUserID())
                        .build();
                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        user.setUserID(result.getId());
                    } else {
                        throw new ElasticException();
                    }
                } catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the user");

                }
            }
            return null;
        }
    }

    public static class GetUser extends AsyncTask<String, Void, User> {
        @Override
        protected User doInBackground(String... strings) {
            verifySettings();
            User foundUser = null;

            Get get = new Get.Builder(indexString, strings[0]) //index, id
                    .type(typeString)
                    .build();

            Log.i("Get", get.toString());
            try {
                JestResult result = client.execute(get);
                if (result.isSucceeded()) {
                    String foundJson = result.getSourceAsString();
                    foundUser = userGson.fromJson(foundJson, User.class);

                } else {
                    throw new ElasticException();
                }
            } catch (ElasticException e) {
                Log.i("Error", "Something went wrong when communicating with the server");
                foundUser = null;
//                foundUser = AppLocale.getInstance().getLocalUser(strings[0]);
            } catch (IOException e) {
                foundUser = null;
//                foundUser = AppLocale.getInstance().getLocalUser(strings[0]);
                Log.i("Error", "Could not connect to the server");
            }
            return foundUser;
        }
    }

    public static class DeleteUser extends  AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            verifySettings();
            Delete delete = new Delete.Builder(strings[0])
                    .index(indexString)
                    .type(typeString)
                    .build();
            try {
                JestResult result = client.execute(delete);
                if (result.isSucceeded()) {
                    Log.i("User", "deleted");
                } else {
                    throw new ElasticException();
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong");
            }
            return null;
        }
    }

    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig
                    //.Builder("localhost:9200");
                    .Builder(serverString);
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }

}
