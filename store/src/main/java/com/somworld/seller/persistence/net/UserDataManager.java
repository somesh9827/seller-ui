package com.somworld.seller.persistence.net;

import com.somworld.seller.persistene.ICallback;

/**
 * Created by somesh.shrivastava on 28/03/15.
 */
public class UserDataManager extends NetworkDataManagerImpl {

  public UserDataManager(ICallback callback) {
    super(callback);
  }

  @Override
  public ICallback getCallBack() {
    return null;
  }

  public void register(){}
  public void login(){}
  public void logout(){}
}
