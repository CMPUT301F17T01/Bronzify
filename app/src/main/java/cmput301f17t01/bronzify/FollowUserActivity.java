package cmput301f17t01.bronzify;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by owenm_000 on 11/1/2017.
 */

public class FollowUserActivity extends Activity {

    private ListView userList;
    private EditText searchBar;
    private Button searchID;
    private Button searchName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // getters and setters
    public ListView getUserList() {
        return userList;
    }

    public void setUserList(ListView userList) {
        this.userList = userList;
    }

    public EditText getSearchBar() {
        return searchBar;
    }

    public void setSearchBar(EditText searchBar) {
        this.searchBar = searchBar;
    }

    public Button getSearchID() {
        return searchID;
    }

    public void setSearchID(Button searchID) {
        this.searchID = searchID;
    }

    public Button getSearchName() {
        return searchName;
    }

    public void setSearchName(Button searchName) {
        this.searchName = searchName;
    }
}
