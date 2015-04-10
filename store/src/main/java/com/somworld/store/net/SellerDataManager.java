package com.somworld.store.net;

import com.somworld.vollywraper.net.NetworkDataManagerImpl;
import com.somworld.vollywraper.persistene.ICallback;

/**
 * Created by somesh.shrivastava on 28/03/15.
 */
public class SellerDataManager extends NetworkDataManagerImpl {

  public SellerDataManager(ICallback callback,Class mapperClass) {
    super(callback,mapperClass);
  }

  public void getSellerInfo(String SellerId){

  }
  public void addSellerInfo(){}
  public void updateSellerInfo(){}

}
