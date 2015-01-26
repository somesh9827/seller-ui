package com.somworld.seller_ui.common.locationHelper;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by somesh.shrivastava on 29/11/14.
 */
public class LocationService implements
        GooglePlayServicesClient.ConnectionCallbacks,GooglePlayServicesClient.OnConnectionFailedListener,LocationListener {

    private LocationRequest mLocationRequest;

    private boolean mUpdatesRequested = false;

    private LocationClient mLocationClient;


    private int mInterval;

    private int mPriority;

    private int mFastestInterval;

    private LocationHelperOnConnectionFailedListener mOnConnectionFailedListener;

    private LocationHelperCallBack mLocationHelperCallBack;

    private Activity mConnectionResolutionCallBackActivity;

    private Context mContext;

    private LocationHelperLocationListener mLocationHelperLocationListener;


    public LocationService(Activity connectionResolutionCallBackActivity, Context context) {

        mInterval = -1;

        mPriority = -1;

        mFastestInterval = -1;

        mConnectionResolutionCallBackActivity = connectionResolutionCallBackActivity;

        mContext = context;
    }


    public void createLocationRequest(LocationHelperCallBack callBackListener, LocationHelperOnConnectionFailedListener connectionFailListener,LocationHelperLocationListener locationHelperLocationListener) {

        mLocationRequest = LocationRequest.create();


        mUpdatesRequested = false;


        mLocationHelperCallBack = callBackListener;


        mOnConnectionFailedListener = connectionFailListener;


        mLocationHelperLocationListener = locationHelperLocationListener;


        mLocationRequest.setInterval(mInterval < 0 ? LocationUtils.UPDATE_INTERVAL_IN_MILLISECONDS : mInterval);


        mLocationRequest.setPriority(mPriority < 0 ? LocationRequest.PRIORITY_HIGH_ACCURACY : mPriority);


        mLocationRequest.setFastestInterval(mFastestInterval < 0 ? LocationUtils.FAST_CEILING_IN_SECONDS : mFastestInterval);


        mLocationClient = new LocationClient(mContext,this,this);
    }

    public LocationService setInterval(int interval) {

        mInterval = interval;

        return this;
    }

    public LocationService setFastestInterval(int fastestInterval) {

        mFastestInterval = fastestInterval;

        return this;
    }

    public LocationService setPriority(int priority) {

        mPriority = priority;

        return this;
    }


    public boolean isUpdatesRequested() {

        return mUpdatesRequested;
    }

    public void setUpdatesRequested(boolean updatesRequested) {

        mUpdatesRequested = updatesRequested;
    }

    public void startPeriodicUpdate() {

      mUpdatesRequested = true;
      Log.d("LocationService/startPeriodicUpdate","start periodic update");
      if(isServicesConnected()) {
          Toast.makeText(mContext,"service  connected",Toast.LENGTH_LONG).show();
          mLocationClient.requestLocationUpdates(mLocationRequest, this);
      }
    }


    public void stopPeriodicUpdates() {

        mUpdatesRequested = false;

        if (isServicesConnected()) {

            mLocationClient.removeLocationUpdates(this);
        }
    }

    public Location getLocation() {

        if(isServicesConnected()) {
            //if(!mLocationClient.isConnected()) return null;
            return mLocationClient.getLastLocation();

        }
      Toast.makeText(mContext,"service is not connected",Toast.LENGTH_LONG).show();
        return null;
    }

    private boolean isServicesConnected() {

        if (mContext == null)
            return false;

        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(mContext);

        if(resultCode == ConnectionResult.SUCCESS) {

            Log.d("Location Updates",
                    "Google Play services is available.");
            // Continue
            return true;
        } else {

          Log.d("Location Updates",
                "Google Play services is not available available.");

        }
        return false;
    }


    public void connect() {

        mLocationClient.connect();
    }

  public boolean isConnect(){
      return mLocationClient.isConnected();
    }
  public boolean isConnecting(){
    return mLocationClient.isConnecting();
  }

  public boolean isWiFiOr3GAvailable(){
    ConnectivityManager connectivityManager =
        (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
    boolean is3g = false,isWifi = false;
    NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
    if(networkInfo != null && networkInfo.isConnectedOrConnecting()) is3g = true;

//For WiFi Check
    networkInfo =  connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
    if(networkInfo != null && networkInfo.isConnectedOrConnecting()) isWifi = true;
    if(!is3g && !isWifi) return false;
    return true;
  }


    public void disConnect() {

        if(mLocationClient.isConnected()) {

            stopPeriodicUpdates();
        }

        mLocationClient.disconnect();
    }

  public boolean isGPSEnabled(){
    if(mContext != null) {
      final LocationManager
          manager = (LocationManager) mContext.getSystemService( Context.LOCATION_SERVICE );
      return manager.isProviderEnabled(LocationManager.GPS_PROVIDER) ? true : false;
    }
    return false;
  }


    @Override
    public void onConnected(Bundle bundle) {

        if(mLocationHelperCallBack != null)
            mLocationHelperCallBack.onConnected(bundle) ;
    }

    @Override
    public void onDisconnected() {

        if(mLocationHelperCallBack != null)
            mLocationHelperCallBack.onDisconnected(); ;
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        if(mConnectionResolutionCallBackActivity == null) return;

        if(connectionResult.hasResolution()) {

            try {

                connectionResult.startResolutionForResult(mConnectionResolutionCallBackActivity,LocationUtils.CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch(IntentSender.SendIntentException e){

                e.printStackTrace();
            }
        } else {
          Log.d("LocationService","Cant find location");
            //showErrorDialog(connectionResult.getErrorCode());
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("Location/service","Location Changed");
        if(mLocationHelperLocationListener != null)
            mLocationHelperLocationListener.onLocationChanged(location);

    }
}
