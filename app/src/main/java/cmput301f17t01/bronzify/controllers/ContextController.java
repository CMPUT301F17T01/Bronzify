package cmput301f17t01.bronzify.controllers;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cmput301f17t01.bronzify.adapters.UserAdapter;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by kdehaan on 02/12/17.
 */

public class ContextController {
    private Context context;
    private Gson gsonUser = new GsonBuilder().registerTypeAdapter(User.class,
            new UserAdapter()).create();

    public ContextController(Context context) {
        this.context = context;
    }



}
