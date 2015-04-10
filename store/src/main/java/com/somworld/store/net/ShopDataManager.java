package com.somworld.store.net;


import com.somworld.store.net.entity.seller.Location;
import com.somworld.store.net.entity.seller.Shop;
import com.somworld.store.net.resource.ShopResource;
import com.somworld.vollywraper.net.NetworkDataManagerImpl;
import com.somworld.vollywraper.net.entity.IEntity;
import com.somworld.vollywraper.persistene.ICallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by somesh.shrivastava on 28/03/15.
 */
public class ShopDataManager<T extends IEntity> extends NetworkDataManagerImpl {

  ShopDataManager(ICallback<T> callback,Class<T> mapperClass) {
    super(callback,mapperClass);

  }

  public void createShop(Shop shop){

  }
  public void updateShopInfo(){

  }

  public void getShopInfo(){

  }

  public void setLocation(String shopId,Location location) {
    String resource = ShopResource.getShopLocation(shopId);
    Map<String,String> postParam = new HashMap<>();
    postParam.put("data",location.toString());
    post(resource,postParam);
  }

}
