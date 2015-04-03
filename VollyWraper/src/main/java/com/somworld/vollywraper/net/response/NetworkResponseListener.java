package com.somworld.vollywraper.net.response;


import com.somworld.vollywraper.net.entity.IEntity;
import com.somworld.vollywraper.persistene.ICallback;

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
