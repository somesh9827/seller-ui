package com.somworld.seller_ui.models;

import java.util.Map;

/**
 * Created by somesh.shrivastava on 25/12/14.
 */
public interface OnCompleteListener {

    public final int SUCCESS = 1, FAIL = -1;


    public void complete(int status, Map<String, Object> data);
}
