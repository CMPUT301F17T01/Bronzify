package cmput301f17t01.bronzify.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.controllers.LoginController;

public class LoginActivity extends AppCompatActivity {
    LoginController controller = new LoginController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.login_button);
        Button registerButton = (Button) findViewById(R.id.register_button);
        final EditText enterId = (EditText) findViewById(R.id.enter_id);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Boolean result = controller.checkLogin(enterId.getText().toString());
                if (result) {
                    Log.i("User", "Found");
                } else {
                    Log.i("User", "Not found");
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });

    }
}
