package cmput301f17t01.bronzify.models;


import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;


import java.util.ArrayList;
import java.util.Date;

import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;

/**
 * Created by kdehaan on 10/11/17.
 */

//// sample elasticsearch code
//                Log.i("Notice", "Setting user");
//                User testUser = new User("TESTUSER");
//                ElasticSearch.PostUser addUserTask
//                = new ElasticSearch.PostUser();
//                addUserTask.execute(testUser);

//                Log.i("Notice", "Getting User");
//                ElasticSearch.GetUser getUserTask
//                = new ElasticSearch.GetUser();
//                getUserTask.execute("TESTUSER");
//                try {
//                User foundUser = getUserTask.get();
//                Log.i("Success", foundUser.toString());
//                } catch (Exception e) {
//                Log.i("Error", "Failed to get the user from the async object");
//                }

public class ElasticSearch {
    private static JestDroidClient client;
    private static String indexString = "cmput301f17t01_bronzify";


    public void postUser(User user) {
        ElasticSearch.PostUser addUserTask
                = new ElasticSearch.PostUser();
        addUserTask.execute(user);
    }

    public User getUser(String userID) {
        User foundUser = null;
        ElasticSearch.GetUser getUserTask
                = new ElasticSearch.GetUser();
        getUserTask.execute(userID);
        try {
            foundUser = getUserTask.get();
        } catch (Exception e) {
            foundUser = null;
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
                        .type("users")
                        .id(user.getUserID())
                        .build();
                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        user.setUserID(result.getId());
                    } else {
                        Log.i("Error", "Elasticsearch was not able to add the user");
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
                    .type("users")
                    .build();

            Log.i("Get", get.toString());
            try {
                JestResult result = client.execute(get);
                if (result.isSucceeded()) {
                    foundUser = result.getSourceAsObject(User.class);

                } else {
                    Log.i("Error", "The search query failed");
                    foundUser = null;
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
            Delete delete = new Delete.Builder(indexString)
                    .type("users")
                    .id(strings[0])
                    .build();
            try {
                JestResult result = client.execute(delete);
                if (result.isSucceeded()) {
                    Log.i("User", "deleted");
                } else {
                    Log.i("Error", "The delete failed");
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
