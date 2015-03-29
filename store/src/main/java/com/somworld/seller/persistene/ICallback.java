package com.somworld.seller.persistene;

import com.somworld.seller.persistence.net.entity.IEntity;
import com.somworld.seller.persistence.net.response.ErrorResponse;

import java.util.List;

/**
 * Created by somesh.shrivastava on 28/03/15.
 */
public interface ICallback<T extends IEntity> {
  public void onObjectResult(T entity);
  public void onError(ErrorResponse error);
  public void onArrayResult(List<T> entityList);

}
