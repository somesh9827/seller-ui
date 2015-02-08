package com.somworld.seller_ui.dataService.networkService;

/**
 * Created by somesh.shrivastava on 11/01/15.
 */
public class NetworkAdapter {
    private static NetworkAdapter ourInstance = new NetworkAdapter();

    public static NetworkAdapter getInstance() {
        return ourInstance;
    }

    private NetworkAdapter() {
    }
}
