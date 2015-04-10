package com.somworld.store.net;

import com.somworld.vollywraper.net.NetworkDataManagerImpl;
import com.somworld.store.net.entity.RoleType;
import com.somworld.vollywraper.persistene.ICallback;

/**
 * Created by somesh.shrivastava on 28/03/15.
 */
public class UserDataManager extends NetworkDataManagerImpl {

  public UserDataManager(ICallback callback,Class mapperClass) {
    super(callback,mapperClass);
  }

  public void register(RoleType role){}
  public void login(RoleType role){}
  public void logout(RoleType role){}
}
