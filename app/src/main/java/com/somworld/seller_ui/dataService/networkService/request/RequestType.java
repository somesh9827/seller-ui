package com.somworld.seller_ui.dataService.networkService.request;

/**
 * Created by somesh.shrivastava on 11/01/15.
 */
public enum RequestType {
    GET_SELLER_INFO(0x01),UPDATE_SELLER_INFO(0x02);

    private int mType;

    RequestType(int type){
        mType = type;
    }

    @Override
    public String toString() {
        return  Integer.toString(mType);
    }
}
