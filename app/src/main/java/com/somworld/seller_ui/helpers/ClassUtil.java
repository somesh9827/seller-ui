package com.somworld.seller_ui.helpers;

/**
 * Created by somesh.shrivastava on 24/01/15.
 */
public class ClassUtil {

  public static String getClassName(Object obj) {
    if (obj == null) return "null";
    String qualifiedName = obj.getClass().getName();
    return !qualifiedName.contains(".") || qualifiedName.lastIndexOf('.')== qualifiedName.length()-1? qualifiedName : qualifiedName.substring(qualifiedName.lastIndexOf('.')+1,qualifiedName.length());
  }
}
