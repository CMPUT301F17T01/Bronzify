package cmput301f17t01.bronzify.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cmput301f17t01.bronzify.R;

/**
 * Created by owenm_000 on 11/1/2017.
 *
 * Landing page for the user when they open the app.
 * Also contains Firebase Auth, to validate user login.
 *
 * TO-DO: Develop front-end for this
 *
 */
public class MyHomeActivity extends Activity {
    private FirebaseAuth mAuth;

    private static final String TAG = "MyHomeActivity";

    private EditText emailText;
    private EditText passwordText;
    FirebaseUser currUser;
    // TODO: Create xml for this activity
    emailText = findViewById(R.id.field_email);
    passwordText = findViewById(R.id.field_password);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home);
    }
    @Override
    public void onStart() {
        super.onStart();
        currUser = mAuth.getCurrentUser();

        if(currUser == null){
            createAccount(emailText.getText().toString(),passwordText.getText().toString());
        }
        else{
            signInAccount(emailText.getText().toString(),passwordText.getText().toString());
        }
    }

    private void createAccount(final String email, String password){
        Log.d(TAG,"account:" + email);
        if(!validateForm()){
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG,"Creating "+ email);
                            currUser = mAuth.getCurrentUser();
                            refreshScreen(currUser);
                        }
                        else{
                            Log.w(TAG, "Error creating");
                            Toast.makeText(MyHomeActivity.this,"Could not create user", Toast.LENGTH_SHORT).show();
                            refreshScreen(null);
                        }
                    }
                }
        );

    }

    private void signInAccount(final String email, String password){
        Log.d(TAG,"account: " + email);

        if(!validateForm()){
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Log.d(TAG,"Signing in "+ email);
                                    currUser = mAuth.getCurrentUser();
                                    refreshScreen(currUser);
                                }
                                else{
                                    Log.w(TAG, "Error creating");
                                    Toast.makeText(MyHomeActivity.this,"Could not create user", Toast.LENGTH_SHORT).show();
                                    refreshScreen(null);
                                }
                            }
                        }
                );
    }

    private void refreshScreen(FirebaseUser user){
        // TODO: Update the screen, hide the EditTexts if the user has logged in.
        if (user == null){
            // Show EditTExts
            return;
        }

        // Change activities once the user has successfully logged in?

    }

    private boolean validateForm() {
        // Simple function to ensure the form is filled out

        boolean valid = true;

        String email = emailText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailText.setError("Required.");
            valid = false;
        } else {
            emailText.setError(null);
        }

        String password = passwordText.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordText.setError("Required.");
            valid = false;
        } else {
            passwordText.setError(null);
        }
        return valid;
    }
}
