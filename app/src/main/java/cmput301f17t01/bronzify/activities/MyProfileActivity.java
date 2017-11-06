package cmput301f17t01.bronzify.activities;

import android.media.Image;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;

import cmput301f17t01.bronzify.R;

/**
 * Created by jblazusi on 2017-11-01.
 */
public class MyProfileActivity extends Activity {


    private String name;
    private String userID;
    private Image picture;
    private Button editProfile;
    private Button sideBar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Image getPicture() {
        return picture;
    }

    public void setPicture(Image picture) {
        this.picture = picture;
    }

    public void displayUserInfo() {
        return;
    }

    public void editProfile() {
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
    }

}
