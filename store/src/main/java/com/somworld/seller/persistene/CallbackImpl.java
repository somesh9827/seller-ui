package com.somworld.seller.persistene;

import android.util.Log;

import com.somworld.seller.persistence.net.entity.IEntity;
import com.somworld.seller.persistence.net.entity.StringMessage;
import com.somworld.seller.persistence.net.response.ErrorResponse;

import org.apache.http.MethodNotSupportedException;

import java.util.List;

/**
 * Created by somesh.shrivastava on 29/03/15.
 */
public abstract class CallbackImpl  implements ICallback<StringMessage>{

  @Override
  public void onObjectResult(StringMessage entity)  {
    Log.d(ModuleConstant.LOG.TAG, "Method ObjectResult is not supported from BaseClass");
  }

  @Override
  public void onArrayResult(List<StringMessage> entityList) {
    Log.d(ModuleConstant.LOG.TAG, "Method ArrayResult is not supported from BaseClass");
  }
}
