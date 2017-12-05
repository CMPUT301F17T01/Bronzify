package cmput301f17t01.bronzify.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Iterator;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.controllers.ElasticSearch;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.HabitEvent;
import cmput301f17t01.bronzify.models.HabitType;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by jblazusi on 2017-11-01.
 */

public class MyMapFragment extends Fragment implements OnMapReadyCallback {
    private User user;
    private Location currentLocation;
    private MapView mMapView;
    private View mView;

    private static final String TAG = "MapActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final float DEFAULT_ZOOM = 15f;

    private static final int DISTANCE = 5000;

    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLocationPermission();
        user = AppLocale.getInstance().getUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.habit_event_tab_map, container, false);
        return mView;
    }

    private void getDeviceLocation() {

        FusedLocationProviderClient mFusedLocationProviderClient;
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        try {
            if (mLocationPermissionsGranted) {
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            currentLocation = (Location) task.getResult();
                            LatLng LatitudeLongitude = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                            moveCamera(LatitudeLongitude);
                            BitmapDescriptor mapBitmap;

                            ElasticSearch elastic = new ElasticSearch();
                            ArrayList<User> users = new ArrayList<>();
                            users.add(user);
                            ArrayList<String> following = user.getFollowing();
                            Iterator<String> strItr = following.iterator();
                            while (strItr.hasNext()) {
                                String next = strItr.next();
                                User foundUser = elastic.getUser(next);
                                if (foundUser != null) {
                                    users.add(foundUser);
                                }
                            }
                            ArrayList<HabitEvent> nearbyEvents = new ArrayList<>();
                            Iterator<User> userItr = users.iterator();
                            while (userItr.hasNext()) {
                                User nextUser = userItr.next();
                                Iterator<HabitType> typeItr = nextUser.getHabitTypes().iterator();
                                while (typeItr.hasNext()) {
                                    HabitType nextType = typeItr.next();
                                    Iterator<HabitEvent> eventItr = nextType.getHabitEvents().iterator();
                                    while (eventItr.hasNext()) {
                                        HabitEvent nextEvent = eventItr.next();
                                        if (nextEvent.getLocation() != null) {
                                            if (currentLocation.distanceTo(nextEvent.getLocation()) < DISTANCE) {
                                                nearbyEvents.add(nextEvent);
                                            }
                                        }
                                    }
                                }

                            }

                            Iterator<HabitEvent> markEvItr = nearbyEvents.iterator();
                            while (markEvItr.hasNext()) {
                                HabitEvent nextMark = markEvItr.next();
                                if (nextMark.getImage() != null) {
                                    mapBitmap = BitmapDescriptorFactory.fromBitmap(nextMark.getImage());
                                } else {
                                    mapBitmap = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);
                                }
                                mMap.addMarker(new MarkerOptions()
                                        .position(LatitudeLongitude)
                                        .title(nextMark.getHabitType())
                                        .snippet(nextMark.getComment())
                                        .icon(mapBitmap));
                            }

//
//                            mMap.addMarker(new MarkerOptions()
//                                    .position(LatitudeLongitude)
//                                    .title(event.getHabitType())
//                                    .snippet(event.getComment())
//                                    .icon(mapBitmap));
                            User user = AppLocale.getInstance().getUser();
                            user.setLocation(currentLocation);
//

                        } else {
                            Log.d(TAG, "onComplete: current location is null");
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
        }
    }

    /**
     * Called when the view is created
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mMapView = mView.findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (mLocationPermissionsGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

        }
    }

    private void moveCamera(LatLng latLng) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, MyMapFragment.DEFAULT_ZOOM));
    }

    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (Integer object : grantResults) {
                        if (object != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionsGranted = true;
                }
            }
        }
    }

   /* public void drawMarker() {
        Drawable circleDrawable = getResources().getDrawable(R.drawable.circle_shape);
        BitmapDescriptor markerIcon = getMarkerIconFromDrawable(circleDrawable);

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.906991, 12.453360))
                .title("My Marker")
                .icon(markerIcon)
        );
    }

    private BitmapDescriptor getMarkerIconFromDrawable(Drawable drawable) {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }*/

}
