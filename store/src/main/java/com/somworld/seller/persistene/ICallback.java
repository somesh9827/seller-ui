package com.somworld.seller.persistene;

import com.somworld.seller.persistence.net.entity.IEntity;
import com.somworld.seller.persistence.net.response.ErrorResponse;

/**
 * Created by somesh.shrivastava on 28/03/15.
 */
public interface ICallback {
  public void onSuccess(IEntity entity);
  public void onError(ErrorResponse error);
}
