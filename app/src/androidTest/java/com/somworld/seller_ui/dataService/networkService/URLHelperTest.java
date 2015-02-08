package com.somworld.seller_ui.dataService.networkService;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by somesh.shrivastava on 11/01/15.
 */
public class URLHelperTest extends TestCase {

    Map<String,String> values;
    String url;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        url = "http://abc.com/:id/a/:iid/b/:name";
        values = new HashMap<String, String>();
        values.put("id","1011");
        values.put("iid","100");
        values.put("name","somesh");

    }

    public void testResolveURLPassWithCorrectArgument() {
        String newURL = URLHelper.resolveURL(url,values);
        String expectedURL = "http://abc.com/1011/a/100/b/somesh";
        assertEquals(expectedURL,newURL);
    }

    public void testResolveURLFailWhenURLIsNull() {
        try{
            String url = null;
            String newURL = URLHelper.resolveURL(url,values);
            fail("Missing Exception");

        }catch (IllegalArgumentException e){
            assertEquals(e.getMessage(),"URL should not be null");
        }
    }

    public void testResolveURLReturnSameURLWhenValuesNULL() {

            String newURL = URLHelper.resolveURL(url,null);
            assertEquals(url,newURL);
    }

    public void testResolveURLReturnSameURLWhenValuesEmpty() {
        Map  values = new HashMap<String, String>();
        String newURL = URLHelper.resolveURL(url,values);
        assertEquals(url,newURL);
    }




}
