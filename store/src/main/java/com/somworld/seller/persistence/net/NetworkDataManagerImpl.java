package com.somworld.seller.persistence.net;

import com.somworld.seller.persistence.net.INetworkDataManager;
import com.somworld.seller.persistene.ICallback;

/**
 * Created by somesh.shrivastava on 28/03/15.
 */
public  class NetworkDataManagerImpl implements INetworkDataManager {
  private final ICallback mCallback;

  public NetworkDataManagerImpl(ICallback callback) {
    mCallback = callback;
  }

  @Override
  public ICallback getCallBack() {
    return mCallback;
  }
}
