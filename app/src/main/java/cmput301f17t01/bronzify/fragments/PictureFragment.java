package cmput301f17t01.bronzify.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;
import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.adapters.ImageAdapter;
import cmput301f17t01.bronzify.controllers.ContextController;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.HabitEvent;
import cmput301f17t01.bronzify.models.User;


/**
 * Created by jblazusi on 2017-11-01.
 */

public class PictureFragment extends Fragment {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PIXELS = 100;

    private HabitEvent event;

    Button button;
    ImageView imageView;

    ImageView circularImageView;

    public PictureFragment() {
        //CONSTRUCTOR
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.habit_event_tab_picture,
                container, false);
        event = AppLocale.getInstance().getEvent();
        button = (Button) rootView.findViewById(R.id.buttonpic);
        imageView = (ImageView) rootView.findViewById(R.id.testing_pic);
        circularImageView = (ImageView) rootView.findViewById(R.id.circleView);

        if (event.getImage() != null) {
            circularImageView.setImageBitmap(event.getImage());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

            }
        });

        return rootView;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == Activity.RESULT_OK) {

                Bitmap bmp = (Bitmap) data.getExtras().get("data");

                Log.d("PHOTO","Size in KB before compression: " + bmp.getByteCount() / 1000);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Log.d("PHOTO","Size in KB after compression: " + byteArray.length / 1000);

                Bitmap circularBitmap = ImageAdapter.getRoundedCornerBitmap(bmp, PIXELS);
                event.setImage(circularBitmap);

                AppLocale appLocale = AppLocale.getInstance();

//                user.updateEvent(event);
//                appLocale.setEvent(event);
                ContextController contextController = new ContextController(getActivity().getApplicationContext());
                contextController.updateUser(appLocale.getUser());
                circularImageView.setImageBitmap(circularBitmap);
            }
        }
    }
}


