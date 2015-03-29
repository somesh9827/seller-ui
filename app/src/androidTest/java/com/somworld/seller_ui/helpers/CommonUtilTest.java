package com.somworld.seller_ui.helpers;

import com.somworld.seller_ui.helpers.Util;

import junit.framework.TestCase;

/**
 * Created by somesh.shrivastava on 29/03/15.
 */
public class CommonUtilTest extends TestCase {

  public void testGetWeakreference(){
    WeakReferenceClass obj1 = new WeakReferenceClass();
     WeakReferenceClass obj2 =  Util.getWeakReference(obj1);
    assertEquals(obj1,obj2);
  }

  private static class WeakReferenceClass {
    private int a;
  }
}
