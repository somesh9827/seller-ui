package com.somworld.vollywraper.net;

import com.somworld.vollywraper.persistene.ICallback;

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
