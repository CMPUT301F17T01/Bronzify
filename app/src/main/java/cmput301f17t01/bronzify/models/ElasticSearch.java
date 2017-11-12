package cmput301f17t01.bronzify.models;


import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * Created by kdehaan on 10/11/17.
 */

public class ElasticSearch {
    private static JestDroidClient client;

    public static class PostUser extends AsyncTask<User, Void, Void> {
        @Override
        protected Void doInBackground(User... users) {
            verifySettings();
            for (User user : users) {
                Index index = new Index.Builder(user)
                        .index("testing")
                        .type("user")
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

    public static class GetUser extends AsyncTask<String, Void, ArrayList<User>> {
        @Override
        protected ArrayList<User> doInBackground(String... strings) {
            verifySettings();
            ArrayList<User> users = new ArrayList<User>();
            Log.i("Search Params", strings[0]);
            Search search = new Search.Builder(strings[0])
                    .addIndex("testing")
                    .addType("user")
                    .build();
            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    User foundUser = result.getSourceAsObject(User.class);
                    users.add(foundUser);
                } else {
                    Log.i("Error", "The search query failed");
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when communicating with the server");
            }
            return users;
        }
    }

    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig
                    .Builder("http://cmput301.softwareprocess.es:8080/cmput301f17t01_bronzify");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }

}
