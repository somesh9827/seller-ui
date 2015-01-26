package com.somworld.seller_ui.common.locationHelper;

import com.google.android.gms.common.ConnectionResult;

/**
 * Created by somesh.shrivastava on 30/11/14.
 */
public interface LocationHelperOnConnectionFailedListener {

    public void onConnectionFailed(ConnectionResult connectionResult);
}

