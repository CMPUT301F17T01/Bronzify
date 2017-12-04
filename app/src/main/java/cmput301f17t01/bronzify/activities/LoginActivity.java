package cmput301f17t01.bronzify.activities;


import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.controllers.ContextController;
import cmput301f17t01.bronzify.controllers.LoginController;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.User;

public class LoginActivity extends AppCompatActivity {
    private LoginController controller = new LoginController();

    /**
     *
     * Called on the creation of the Login Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ContextController contextController = new ContextController(getApplicationContext());
        AppLocale appLocale = AppLocale.getInstance();
        appLocale.setLocalUsers(contextController.loadFromFile());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.login_button);
        Button registerButton = (Button) findViewById(R.id.register_button);
        final EditText enterId = (EditText) findViewById(R.id.enter_id);
        final TextView loginInfo = (TextView) findViewById(R.id.login_info);

        //LOGIN BUTTON
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String input = enterId.getText().toString();
                if (input.trim().length() == 0) {
                    loginInfo.setVisibility(View.VISIBLE);
                    loginInfo.setText("Please enter a user ID");
                    return;
                }
                User result = controller.checkLogin(input);
                if (result != null) {
                    Log.i("User", "Found");
                    controller.loginUser(result);
                    contextController.saveUser(result);
                    loginInfo.setVisibility(View.VISIBLE);
                    loginInfo.setText("Login Successful");
                    Intent intent = new Intent(LoginActivity.this, MyHomeActivity.class);
                    startActivity(intent);

                } else {
                    loginInfo.setVisibility(View.VISIBLE);
                    loginInfo.setText("Invalid User ID");

                }
            }
        });

        //REGISTER BUTTON
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String input = enterId.getText().toString();
                if (input.trim().length() == 0) {
                    loginInfo.setVisibility(View.VISIBLE);
                    loginInfo.setText("Please enter a user ID");
                    return;
                }
                User result = controller.checkLogin(input);
                if (result == null) { //opposite of login
                    Log.i("User", "Not Found");
                    controller.registerUser(input);
                    loginInfo.setVisibility(View.VISIBLE);
                    loginInfo.setText("Registration Successful");
                } else {
                    loginInfo.setVisibility(View.VISIBLE);
                    loginInfo.setText("User ID already exists");

                }
            }
        });


    }
}
