package com.somworld.seller.persistence.net;

import com.somworld.seller.persistence.net.entity.RoleType;
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

  public void register(RoleType role){}
  public void login(RoleType role){}
  public void logout(RoleType role){}
}
