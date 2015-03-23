package com.somworld.seller_ui.dataService;

import com.somworld.seller_ui.models.dtos.ShopLocationDTO;

/**
 * Created by somesh.shrivastava on 14/03/15.
 */
public class ShopDataServices extends DataManager {

  public ShopDataServices(IDataServiceCallback IDataServiceCallback) {
    super(IDataServiceCallback);
  }

  public void setShopLocation(ShopLocationDTO shopLocationDTO){
    //write code to data access
    boolean success = true;
    if(success) {
      DataServiceSuccessResponse dataServiceSuccessResponse = new DataServiceSuccessResponse();
      dataServiceSuccessResponse.setCode(200);
      dataServiceSuccessResponse.setMessage("Shop Location has been changed");
      dataServiceSuccessResponse.setData(shopLocationDTO);
      mIDataServiceCallback.onDataServiceSuccess(dataServiceSuccessResponse);
    }
    else {
      DataServiceErrorResponse dataServiceErrorResponse = new DataServiceErrorResponse();
      dataServiceErrorResponse.setErrorCode(400);
      dataServiceErrorResponse.setMessage("problem in server");
      mIDataServiceCallback.onDataServiceFail(dataServiceErrorResponse);
    }
  }
}
