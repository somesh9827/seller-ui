package com.somworld.store.net;

import com.somworld.vollywraper.net.NetworkDataManagerImpl;
import com.somworld.vollywraper.net.entity.IEntity;
import com.somworld.vollywraper.persistene.ICallback;

/**
 * Created by somesh.shrivastava on 28/03/15.
 */
public class OfferDataManager<T extends IEntity> extends NetworkDataManagerImpl {

    public OfferDataManager(ICallback<T> callback,Class<T> mapperClass) {
      super(callback,mapperClass);
    }

  public void createOffer(){}
  public void updateOffer(){}
  public void deleteOffer(){}
  public void getOffers(){}
  public void changeOfferStatus(){}

}
