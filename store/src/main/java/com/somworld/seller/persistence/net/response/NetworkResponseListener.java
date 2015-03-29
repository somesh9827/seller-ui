package com.somworld.seller.persistence.net.response;


import com.somworld.seller.persistence.net.entity.IEntity;
import com.somworld.seller.persistene.ICallback;

import org.json.JSONObject;

/**
 * Created by somesh.shrivastava on 28/03/15.
 */
public class NetworkResponseListener<T extends IEntity> {

  protected ICallback<T> mCallback;

  protected Class<T> mJSONMapperClass;


  public NetworkResponseListener(ICallback callback, Class jsonMapperClass) {
    mCallback = callback;
    mJSONMapperClass = jsonMapperClass;
  }

  public static  enum RESPONSE_STATUS{
  SUCCESS,
  FAIL
};
}
