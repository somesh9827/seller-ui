package com.somworld.seller_ui.dataService;

/**
 * Created by somesh.shrivastava on 14/03/15.
 */
public interface IDataServiceCallback {

  void onDataServiceSuccess(DataServiceSuccessResponse dataServiceSuccessResponse);

  void onDataServiceFail(DataServiceErrorResponse dataServiceErrorResponse);

}
