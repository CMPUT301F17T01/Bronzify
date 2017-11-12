package cmput301f17t01.bronzify.activities;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.controllers.LoginController;
import cmput301f17t01.bronzify.models.Locale;
import cmput301f17t01.bronzify.models.User;

public class LoginActivity extends AppCompatActivity {
    private LoginController controller = new LoginController();
    private Locale locale = Locale.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.login_button);
        Button registerButton = (Button) findViewById(R.id.register_button);
        final EditText enterId = (EditText) findViewById(R.id.enter_id);
        final TextView loginInfo = (TextView) findViewById(R.id.login_info);


        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String input = enterId.getText().toString();
                User result = controller.checkLogin(input);
                if (result != null) {
                    Log.i("User", "Found");
                    controller.loginUser(result);
                    loginInfo.setVisibility(View.VISIBLE);
                    loginInfo.setText("Login Successful");

                } else {
                    loginInfo.setVisibility(View.VISIBLE);
                    loginInfo.setText("Invalid User ID");
//                    AlertDialog.Builder adBuilder = new AlertDialog.Builder(LoginActivity.this);
//                    adBuilder.setMessage(R.string.login_fail_message);
//                    adBuilder.setNegativeButton(R.string.dialog_return, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                        }
//                    });
//                    AlertDialog alertDialog = adBuilder.create();
//                    alertDialog.show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String input = enterId.getText().toString();
                User result = controller.checkLogin(input);
                if (result == null) { //opposite of login
                    Log.i("User", "Not Found");
                    controller.registerUser(input);
                    loginInfo.setVisibility(View.VISIBLE);
                    loginInfo.setText("Registration Successful");
                } else {
                    loginInfo.setVisibility(View.VISIBLE);
                    loginInfo.setText("User ID already exists");

//                    AlertDialog.Builder adBuilder = new AlertDialog.Builder(LoginActivity.this);
//                    adBuilder.setMessage(R.string.register_fail_message);
//                    adBuilder.setNegativeButton(R.string.dialog_return, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                        }
//                    });
//                    AlertDialog alertDialog = adBuilder.create();
//                    alertDialog.show();
                }
            }
        });

    }
}
