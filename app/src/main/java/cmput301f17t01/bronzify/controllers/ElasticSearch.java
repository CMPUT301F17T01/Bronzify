package cmput301f17t01.bronzify.controllers;


import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;


import java.util.Date;

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
    private static String indexString = "cmput301f17t01_bronzify";
    private static String typeString = "test_user";



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
        User remoteUser = getUser(otherUserID);
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

    public User getUser(String userID) {
        User foundUser;
        ElasticSearch.GetUser getUserTask
                = new ElasticSearch.GetUser();
        getUserTask.execute(userID);
        try {
            foundUser = getUserTask.get();
        } catch (Exception e) {
            foundUser = null;
        }
        if (foundUser == null) {
            foundUser = AppLocale.getInstance().getSavedUser(userID);
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
                Index index = new Index.Builder(user)
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
            User foundUser;

            Get get = new Get.Builder(indexString, strings[0]) //index, id
                    .type(typeString)
                    .build();

            Log.i("Get", get.toString());
            try {
                JestResult result = client.execute(get);
                if (result.isSucceeded()) {
                    foundUser = result.getSourceAsObject(User.class);

                } else {
                    throw new ElasticException();
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when communicating with the server");
                foundUser = null;
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
                    .Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }

}