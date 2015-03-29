package com.somworld.seller.persistence.net.response;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;


import com.somworld.seller.common.ContextManager;
import com.somworld.seller.persistence.net.entity.IEntity;
import com.somworld.seller.persistence.net.error.NetworkError;
import com.somworld.seller.persistene.ICallback;
import com.somworld.seller.persistene.ModuleConstant;


import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.NullNode;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by somesh.shrivastava on 29/03/15.
 */
public class StringNetworkresponseListenr<T extends IEntity> extends NetworkResponseListener<T> implements Response.Listener<String>,
                                                                      Response.ErrorListener {

  public StringNetworkresponseListenr(ICallback<T> callback, Class<T> jsonMapperClass) {
    super(callback, jsonMapperClass);
  }

  @Override
  public void onResponse(String response) {
     ObjectMapper mapper = new ObjectMapper();
    try {
      JsonNode rootNode = mapper.readTree(response);
      switch (findResponseStatus(rootNode)) {
        case SUCCESS:
          buildPositiveResponse(rootNode.path("data"));
          break;
        case FAIL:
          buildErrorResponse(rootNode.path("error"));
          break;
      }
    } catch (IOException e) {
      Log.d(ModuleConstant.LOG.TAG,e.getMessage());
      ErrorResponse errorResponse = new ErrorResponse(NetworkError.CODE.INCORRECT_DATA,
                                                      NetworkError.MESSAGE.INCORRECT_DATA);
      mCallback.onError(errorResponse);
    }
  }

  private void onErrorResponse(ErrorResponse errorResponse) {
    mCallback.onError(errorResponse);
  }

  @Override
  public void onErrorResponse(VolleyError error) {
    Context context = ContextManager.getInstance().getContext();
    if(context != null) {
      ErrorResponse errorResponse  = null;
      if (error instanceof TimeoutError || error instanceof NoConnectionError) {

        Log.d(ModuleConstant.LOG.TAG, NetworkError.MESSAGE.NETWORK_TIMEOUT);
        errorResponse = new ErrorResponse(NetworkError.CODE.NETWORK_TIMEOUT,NetworkError.MESSAGE.NETWORK_TIMEOUT);

      }
      else if (error instanceof AuthFailureError) {
        Log.d(ModuleConstant.LOG.TAG, NetworkError.MESSAGE.AUTHENTICATION_FAIL);
        errorResponse = new ErrorResponse(NetworkError.CODE.AUTHENTICATION_FAIL,NetworkError.MESSAGE.AUTHENTICATION_FAIL);

      }
      else if (error instanceof ServerError) {
        Log.d(ModuleConstant.LOG.TAG, NetworkError.MESSAGE.SERVER_ERROR);
        errorResponse = new ErrorResponse(NetworkError.CODE.SERVER_ERROR,NetworkError.MESSAGE.SERVER_ERROR);

      }
      else if (error instanceof NetworkError) {
        Log.d(ModuleConstant.LOG.TAG, NetworkError.MESSAGE.SERVER_ERROR);
        errorResponse = new ErrorResponse(NetworkError.CODE.NETWORK_ERROR,NetworkError.MESSAGE.NETWORK_ERROR);

      }
      else if (error instanceof ParseError) {
        Log.d(ModuleConstant.LOG.TAG, NetworkError.MESSAGE.PARSE_ERROR);
        errorResponse = new ErrorResponse(NetworkError.CODE.PARSE_ERROR,NetworkError.MESSAGE.PARSE_ERROR);
      }
      mCallback.onError(errorResponse);
    }

  }

  private RESPONSE_STATUS findResponseStatus(JsonNode rootNode) throws IOException{
    RESPONSE_STATUS responseStatus;
    try {
      JsonNode statusNode = rootNode.path("status");

      if(statusNode == null) throw new IOException("NO Status field found in Response");
      responseStatus = Enum.valueOf(RESPONSE_STATUS.class,statusNode.asText().toUpperCase());
      if(responseStatus == null)
        throw new IOException("Incorrect status filed. Expecting SUCCESS or FAIL");
    } catch (IOException e) {
      throw e;
    }
    return responseStatus;
  }

  private void buildErrorResponse(JsonNode node) throws IOException{
    if(node.equals(NullNode.getInstance())) throw new IOException("No Error field found while status field is FAIL");
    ObjectMapper mapper = new ObjectMapper();
    ErrorResponse errorResponse = mapper.readValue(node,ErrorResponse.class);
    mCallback.onError(errorResponse);
  }

  private void buildPositiveResponse(JsonNode dataNode) throws IOException {
    if(dataNode == null) throw new JsonGenerationException("No data field found in Response data");
    if(dataNode.isArray()) {
     List<T> entities =  mapJsonArrayToList(dataNode);
     mCallback.onArrayResult(entities);
    }
    else {
      T entity = mapJsonObjectToEntityObject(dataNode);
      mCallback.onObjectResult(entity);
    }
  }

  private List<T> mapJsonArrayToList(JsonNode dataNode) throws IOException{
    ObjectMapper mapper = new ObjectMapper();
    int i = 0;
    JsonNode node;
    T object;
    List<T> list = new ArrayList<T>();
    while((node = dataNode.get(i))!= null){
      object = mapper.readValue(node,mJSONMapperClass);
      list.add(object);
      i++;
    }
    return list;
  }

  private T mapJsonObjectToEntityObject(JsonNode dataNode) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(dataNode,mJSONMapperClass);
  }
}
