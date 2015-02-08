package com.somworld.seller_ui.dataService.networkService;

/**
 * Created by somesh.shrivastava on 11/01/15.
 */
public final class Host {

    private static final String DEFAULT_HOST = "http://192.168.42.230";

    private static final String DEFAULT_APP =  "/Somworld/";

    private static final String DEFAULT_PORT = "80";

    //public static final
    public String getDefaultURL() {

        return DEFAULT_HOST + ":" + DEFAULT_PORT + DEFAULT_APP;
    }

}
