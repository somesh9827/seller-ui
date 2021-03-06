package com.somworld.seller_ui.common.locationHelper;

import com.google.android.gms.maps.model.*;

import android.content.Context;
import android.location.Location;

import com.somworld.seller_ui.R;

/**
 * Created by somesh.shrivastava on 28/11/14.
 */
public final class LocationUtils {

    // Create an empty string for initializing strings
    public static final String EMPTY_STRING = new String("empty string");

    public static final String LAT_LNG_FORMAT = "%1.8f, %2.8f";

    /**
     * Get the latitude and longitude from the Location object returned by
     * Location Services.
     *
     * @param currentLocation A Location object containing the current location
     * @return The latitude and longitude of the current location, or null if no
     * location is available.
     */
    public static String getLatLngString(Context context, Location currentLocation) {
        // If the location is valid
        if (currentLocation != null && context != null) {

            // Return the latitude and longitude as strings
            return context.getString(
                    //R.string.latitude_longitude,
                    R.string.latitude_longitude,
                    currentLocation.getLatitude(),
                    currentLocation.getLongitude());
        } else {

            // Otherwise, return the empty string
            return EMPTY_STRING;
        }
    }

  public static LatLng getLatLng(Location currentLocation) {
    if (currentLocation != null) {
      LatLng location = new LatLng();
      location.setLatitude(currentLocation.getLatitude());
      location.setLongitude(currentLocation.getLatitude());
      return location;
    } else {
      return null;
    }
  }

    // Debugging tag for the application
    public static final String APPTAG = "LocationSample";

    // Name of shared preferences repository that stores persistent state
    public static final String SHARED_PREFERENCES =
            "com.example.android.location.SHARED_PREFERENCES";

    // Key for storing the "updates requested" flag in shared preferences
    public static final String KEY_UPDATES_REQUESTED =
            "com.example.android.location.KEY_UPDATES_REQUESTED";

    /*
     * Define a request code to send to Google Play services
     * This code is returned in Activity.onActivityResult
     */
    public final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    /*
     * Constants for location update parameters
     */
    // Milliseconds per second
    public static final int MILLISECONDS_PER_SECOND = 1000;

    // The update interval
    public static final int UPDATE_INTERVAL_IN_SECONDS = 5;

    // A fast interval ceiling
    public static final int FAST_CEILING_IN_SECONDS = 1;

    // Update interval in milliseconds
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS =
            MILLISECONDS_PER_SECOND * UPDATE_INTERVAL_IN_SECONDS;

    public static final long FAST_INTERVAL_CEILING_IN_MILLISECONDS = MILLISECONDS_PER_SECOND * FAST_CEILING_IN_SECONDS;


}
