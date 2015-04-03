package com.somworld.vollywraper.persistene;

import com.somworld.vollywraper.net.entity.IEntity;
import com.somworld.vollywraper.net.response.ErrorResponse;

import java.util.List;

/**
 * Created by somesh.shrivastava on 28/03/15.
 */
public interface ICallback<T extends IEntity> {
  public void onObjectResult(T entity);
  public void onError(ErrorResponse error);
  public void onArrayResult(List<T> entityList);

}
