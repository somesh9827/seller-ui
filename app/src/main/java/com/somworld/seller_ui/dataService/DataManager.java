package com.somworld.seller_ui.dataService;

/**
 * Created by somesh.shrivastava on 14/03/15.
 */
public abstract class DataManager {

  protected IDataServiceCallback mIDataServiceCallback;

  public DataManager(IDataServiceCallback IDataServiceCallback) {
    mIDataServiceCallback = IDataServiceCallback;
  }

}
