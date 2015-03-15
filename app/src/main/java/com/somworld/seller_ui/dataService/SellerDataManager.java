package com.somworld.seller_ui.dataService;

import com.somworld.seller_ui.models.RegisterModel;
import com.somworld.seller_ui.models.dtos.LoginDetailDTO;

/**
 * Created by somesh.shrivastava on 14/03/15.
 */
public class SellerDataManager extends DataManager {

  public SellerDataManager(IDataServiceCallback IDataServiceCallback){
    super(IDataServiceCallback);
  }

  public void register(RegisterModel registerModel){
    //write code to data access
    boolean success = true;
    if(success) {
      DataServiceSuccessResponse dataServiceSuccessResponse = new DataServiceSuccessResponse();
      dataServiceSuccessResponse.setCode(200);
      dataServiceSuccessResponse.setMessage("Seller has been Registered");
      dataServiceSuccessResponse.setData(registerModel);
      mIDataServiceCallback.onDataServiceSuccess(dataServiceSuccessResponse);
    }
    else {
      DataServiceErrorResponse dataServiceErrorResponse = new DataServiceErrorResponse();
      dataServiceErrorResponse.setErrorCode(400);
      dataServiceErrorResponse.setMessage("problem in server");
      mIDataServiceCallback.onDataServiceFail(dataServiceErrorResponse);
    }
  }

  public void login(LoginDetailDTO loginDetailDTO){
    //write code to data access
    boolean success = true;
    if(success) {
      DataServiceSuccessResponse dataServiceSuccessResponse = new DataServiceSuccessResponse();
      dataServiceSuccessResponse.setCode(200);
      dataServiceSuccessResponse.setMessage("Seller has been login");
      dataServiceSuccessResponse.setData(loginDetailDTO);
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
