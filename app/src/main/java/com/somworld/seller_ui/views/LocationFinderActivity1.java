package com.somworld.seller_ui.views;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationRequest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.common.locationHelper.LocationHelperCallBack;
import com.somworld.seller_ui.common.locationHelper.LocationHelperLocationListener;
import com.somworld.seller_ui.common.locationHelper.LocationService;
import com.somworld.seller_ui.common.locationHelper.LocationUtils;

import java.lang.ref.WeakReference;


public class LocationFinderActivity1 extends FragmentActivity {

  private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

  private final static int GPS_LOC = 0x5;

  private final static int WIRELESS_SETTING = 0x10;

  private ConnectionCallBackListener mConnectionCallBackListener;

  private LocationRequest mLocationRequest;

  private LocationService locationHelper;
  private Boolean showImmidateLocationResponse = false;


  private TextView mLatLng;
  private TextView mAddress;
  private ProgressBar mActivityIndicator;
  private TextView mConnectionState;
  private TextView mConnectionStatus;

  SharedPreferences mPrefs;

  SharedPreferences.Editor mEditor;


  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location);

    mLatLng = (TextView) findViewById(R.id.lat_lng);
    mAddress = (TextView) findViewById(R.id.address);
    mActivityIndicator = (ProgressBar) findViewById(R.id.address_progress);
    mConnectionState = (TextView) findViewById(R.id.text_connection_state);
    mConnectionStatus = (TextView) findViewById(R.id.text_connection_status);

    WeakReference<Context> contextWeakReference = new WeakReference<Context>(this);
    WeakReference<LocationFinderActivity1>
        activityWeakReference =
        new WeakReference<LocationFinderActivity1>(this);
    locationHelper = new LocationService(activityWeakReference.get(), contextWeakReference.get());

    ConnectionCallBackListener
        connectionCallBackListener =
        new ConnectionCallBackListener(activityWeakReference.get());

    ConnectionListener connectionListener =
        new ConnectionListener(activityWeakReference.get());
    locationHelper.createLocationRequest(connectionCallBackListener, null, connectionListener);

    mPrefs = getSharedPreferences(LocationUtils.SHARED_PREFERENCES, Context.MODE_PRIVATE);

    mEditor = mPrefs.edit();


  }

  @Override
  public void onStop() {
    super.onStop();
    //locationHelper.disConnect();
  }


  @Override
  public void onPause() {

    //mEditor.putBoolean(LocationUtils.KEY_UPDATES_REQUESTED,locationHelper.isUpdatesRequested());
    //mEditor.commit();
    super.onPause();
    locationHelper.disConnect();
  }


  @Override
  public void onStart() {

    super.onStart();
    //locationHelper.connect();
  }


  @Override
  public void onResume() {

    super.onResume();
    locationHelper.connect();
    // If the app already has a setting for getting location updates, get it
   /* if (mPrefs.contains(LocationUtils.KEY_UPDATES_REQUESTED)) {
      locationHelper
          .setUpdatesRequested(mPrefs.getBoolean(LocationUtils.KEY_UPDATES_REQUESTED, false));
      // Otherwise, turn off location updates until requested
    } else {
      mEditor.putBoolean(LocationUtils.KEY_UPDATES_REQUESTED, false);
      mEditor.commit();
    }*/
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.location, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }


  public static class ErrorDialogFragment extends DialogFragment {

    private Dialog mDialog;

    public ErrorDialogFragment() {
      super();
      mDialog = null;
    }

    public void setDialog(Dialog dialog) {
      mDialog = dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
      return mDialog;
    }

  }

  @Override
  protected void onActivityResult(int reqCode, int resCode, Intent Data) {
    Toast.makeText(this," "+reqCode,Toast.LENGTH_LONG).show();
    switch (reqCode) {
      case CONNECTION_FAILURE_RESOLUTION_REQUEST:
        switch (resCode) {
          case Activity.RESULT_OK:

            mConnectionState.setText(R.string.connected);
            mConnectionStatus.setText(R.string.resolved);
            break;

          default:

            mConnectionState.setText(R.string.disconnected);
            mConnectionStatus.setText(R.string.no_resolution);
            break;
        }
        break;
      case GPS_LOC:
        onReturnFromGPSSetting();
        break;
      case WIRELESS_SETTING :
        onReturnFromWirelessSetting();
        break;

      default:

        Log.d(LocationUtils.APPTAG,
              getString(R.string.unknown_activity_request_code, reqCode));
        break;

    }
  }

  private void onReturnFromWirelessSetting(){
    if (locationHelper.isWiFiOr3GAvailable()) {
      if(locationHelper.isConnecting())
        synchronized (showImmidateLocationResponse){
          showImmidateLocationResponse = true;
        }
      else if(!locationHelper.isConnect()){
        synchronized (showImmidateLocationResponse){
          showImmidateLocationResponse = true;
          locationHelper.connect();
        }
      }
      else {
        synchronized (showImmidateLocationResponse){
          showImmidateLocationResponse = false;
          getLocation();
        }
      }
      //getLocation();
    } else {
      Toast.makeText(this, "Wifi is not Enabled", Toast.LENGTH_LONG).show();
    }
  }

  private void onReturnFromGPSSetting() {
    if (locationHelper.isGPSEnabled()) {
       if(locationHelper.isConnecting())
         synchronized (showImmidateLocationResponse){
           showImmidateLocationResponse = true;
         }
       else if(!locationHelper.isConnect()){
         synchronized (showImmidateLocationResponse){
           showImmidateLocationResponse = true;
           locationHelper.connect();
         }
       }
       else {
         synchronized (showImmidateLocationResponse){
           showImmidateLocationResponse = false;
           getLocation();
         }
       }
       //getLocation();
    } else {
      Toast.makeText(this, "GPS is not Enabled", Toast.LENGTH_LONG).show();
    }
  }


  private boolean servicesConnected() {

    int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

    if (resultCode == ConnectionResult.SUCCESS) {

      Log.d("Location Updates",
            "Google Play services is available.");
      // Continue
      return true;
    } else {

      Dialog errorDialog = GooglePlayServicesUtil
          .getErrorDialog(resultCode, this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
      ErrorDialogFragment errorFrament = new ErrorDialogFragment();
      errorFrament.setDialog(errorDialog);
      errorFrament.show(getFragmentManager(), LocationUtils.APPTAG);

    }
    return false;
  }


  public void getLocation(View v) {
    getLocation();
  }

  public void getLocation() {
    Log.d("Location", "getLocation Called");
    if(!locationHelper.isGPSEnabled()){
      showGPSStartDialog();
    }
    else {
      if(!locationHelper.isWiFiOr3GAvailable())
      {
        showWifiOr3GDialog();
      }
      else
      locationHelper.startPeriodicUpdate();
    }
  }

  private void showGPSStartDialog() {
    new AlertDialog.Builder(this).setMessage("GPS is switched off")
        .setPositiveButton("Enable GPS", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, 5);
          }
        }).show();
  }

  private void showWifiOr3GDialog() {
    new AlertDialog.Builder(this).setMessage("Network is not connected")
        .setPositiveButton("Enable WiFi or 3G", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            startActivity(intent);
          }
        }).show();
  }

  private void showErrorDialog(int errorCode) {
    Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
        errorCode,
        this,
        CONNECTION_FAILURE_RESOLUTION_REQUEST);

    // If Google Play services can provide an error dialog
    if (errorDialog != null) {

      // Create a new DialogFragment in which to show the error dialog
      ErrorDialogFragment errorFragment = new ErrorDialogFragment();

      // Set the dialog in the DialogFragment
      errorFragment.setDialog(errorDialog);

      // Show the error dialog in the DialogFragment
      errorFragment.show(getFragmentManager(), "Location Update");
    }
  }

  private static class ConnectionCallBackListener implements LocationHelperCallBack {

    private LocationFinderActivity1 mCurrentActivity;

    ConnectionCallBackListener(LocationFinderActivity1 currentActivity) {

      mCurrentActivity = currentActivity;
    }

    @Override
    public void onConnected(Bundle bundle) {

      if (mCurrentActivity != null) {
        synchronized (mCurrentActivity.showImmidateLocationResponse) {
          if (mCurrentActivity.showImmidateLocationResponse) {
            mCurrentActivity.getLocation();
            mCurrentActivity.showImmidateLocationResponse = false;
          }
        }
        mCurrentActivity.mConnectionStatus.setText(R.string.connected);

      }
    }

    @Override
    public void onDisconnected() {

      if (mCurrentActivity != null) {

        mCurrentActivity.mConnectionStatus.setText(R.string.disconnected);
      }
    }
  }

  private static class ConnectionListener implements LocationHelperLocationListener {

    private LocationFinderActivity1 mCurrentActivity;


    ConnectionListener(LocationFinderActivity1 currentActivity) {

      mCurrentActivity = currentActivity;
    }


    @Override
    public void onLocationChanged(Location location) {
      if(mCurrentActivity != null) {

        mCurrentActivity.mConnectionStatus.setText(R.string.location_updated);

        mCurrentActivity.mLatLng.setText(LocationUtils.getLatLngString(mCurrentActivity, location));
      }
      mCurrentActivity.locationHelper.stopPeriodicUpdates();
    }
  }


}
