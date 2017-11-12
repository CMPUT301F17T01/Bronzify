package cmput301f17t01.bronzify.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.controllers.LoginController;
import cmput301f17t01.bronzify.models.Locale;
import cmput301f17t01.bronzify.models.User;

public class LoginActivity extends AppCompatActivity {
    LoginController controller = new LoginController();
    Locale locale = Locale.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.login_button);
        Button registerButton = (Button) findViewById(R.id.register_button);
        EditText enterId = (EditText) findViewById(R.id.enter_id);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText enterId = (EditText) findViewById(R.id.enter_id);
                String input = enterId.getText().toString();
                User result = controller.checkLogin(input);
                if (result != null) {
                    Log.i("User", "Found");
                    controller.loginUser(result);
                    Log.i("User is", locale.getUser().toString());
                } else {
                    AlertDialog.Builder adBuilder = new AlertDialog.Builder(LoginActivity.this);
                    adBuilder.setMessage(R.string.login_fail_message);
                    adBuilder.setNegativeButton(R.string.dialog_return, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog alertDialog = adBuilder.create();
                    alertDialog.show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText enterId = (EditText) findViewById(R.id.enter_id);
                String input = enterId.getText().toString();
                User result = controller.checkLogin(input);
                if (result == null) { //opposite of login
                    Log.i("User", "Not Found");
                    controller.registerUser(input);
                } else {
                    AlertDialog.Builder adBuilder = new AlertDialog.Builder(LoginActivity.this);
                    adBuilder.setMessage(R.string.register_fail_message);
                    adBuilder.setNegativeButton(R.string.dialog_return, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog alertDialog = adBuilder.create();
                    alertDialog.show();
                }
            }
        });

    }
}
