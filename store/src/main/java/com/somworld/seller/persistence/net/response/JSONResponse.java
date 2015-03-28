package com.somworld.seller.persistence.net.response;

import com.somworld.seller.persistence.net.entity.IEntity;

import org.json.JSONObject;

/**
 * Created by somesh.shrivastava on 28/03/15.
 */
public class JSONResponse {



public  enum RESPONSE_STATUS{
  SUCCESS,
  FAIL
};

private RESPONSE_STATUS status;

private IEntity body;

private  Error error;

}
