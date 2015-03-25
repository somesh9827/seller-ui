package com.somworld.seller.persistence.net.resource.Rest;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by somesh.shrivastava on 25/03/15.
 */
public class RESTResourcebuilderTest extends TestCase {

  public void testBuildResourcePassed() throws Exception {
    String unqualifiedResource = "/sellers/{seller_id}/";
    String expectedOutput = "http://www.google.com:80/sellers/10/";
    List<String> placeholders = new ArrayList<String>();
    placeholders.add("10");
    RestResourceConfiguration configuration = new RestResourceConfiguration(unqualifiedResource).setPlaceholders(placeholders);
    String actualOutput = RESTResourceBuilder.build(configuration);
    assertEquals(expectedOutput, actualOutput);
  }

  public void testBuildResourcePassedWithParam() throws Exception {
    String unqualifiedResource = "/sellers/{seller_id}/";
    String expectedOutput = "http://www.google.com:80/sellers/10/?a=1&b=22";
    List<String> placeholders = new ArrayList<String>();
    placeholders.add("10");
    List<RESTResourceParam> param= new ArrayList<RESTResourceParam>();
    param.add(new RESTResourceParam("a","1"));
    param.add(new RESTResourceParam("b","22"));
    RestResourceConfiguration configuration = new RestResourceConfiguration(unqualifiedResource).setPlaceholders(placeholders).setResourceParam(param);
    String actualOutput = RESTResourceBuilder.build(configuration);
    assertEquals(expectedOutput, actualOutput);
  }

  public void testBuildResourcePassedWith2Placeholder() throws Exception {
    String unqualifiedResource = "/sellers/{seller_id}/shop/{shop_id}";
    String expectedOutput = "http://www.google.com:80/sellers/10/shop/11";
    List<String> placeholders = new ArrayList<String>();
    placeholders.add("10");
    placeholders.add("11");
    RestResourceConfiguration configuration = new RestResourceConfiguration(unqualifiedResource).setPlaceholders(placeholders);
    String actualOutput = RESTResourceBuilder.build(configuration);
    assertEquals(expectedOutput, actualOutput);
  }

  public void testBuildResourceFailLessPlaceholderName() throws Exception {
    try {
      String unqualifiedResource = "/sellers/";
      List<String> placeholders = new ArrayList<String>();
      placeholders.add("10");
      RestResourceConfiguration
          configuration =
          new RestResourceConfiguration(unqualifiedResource).setPlaceholders(placeholders).setPlaceholders(placeholders);
      String actualOutput = RESTResourceBuilder.build(configuration);
      //assertEquals(expectedOutput, actualOutput);
      fail();
    }catch (ResourceBuilderException e) {
      assertEquals("Wrong Number of placeholders",e.getMessage());
    }
  }

  public void testBuildResourceFailLessPlaceholder() throws Exception {
    try {
      String unqualifiedResource = "/sellers/{seller_id}";
      RestResourceConfiguration
          configuration =
          new RestResourceConfiguration(unqualifiedResource);
      String actualOutput = RESTResourceBuilder.build(configuration);
      fail();
    }catch (ResourceBuilderException e) {
      assertEquals("Wrong Number of placeholders",e.getMessage());
    }
  }

  public void testBuildResourceInCorrectUnqualifiedResource() throws Exception {
    try {
      String unqualifiedResource = "/sellers/{seller_id";
      List<String> placeholders = new ArrayList<String>();
      placeholders.add("10");
      RestResourceConfiguration
          configuration =
          new RestResourceConfiguration(unqualifiedResource).setPlaceholders(placeholders);
      String actualOutput = RESTResourceBuilder.build(configuration);
      fail();
    }catch (ResourceBuilderException e) {
      assertEquals("Incorrect UnQualified Resource",e.getMessage());
    }
  }



}
