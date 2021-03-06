/**
 * Copyright (C) 2007 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.inject.tools.suite.module;

/**
 * Utility for manipulating class names.
 * 
 * @author Darren Creutz (dcreutz@gmail.com)
 */
public class ClassNameUtility {
  /**
   * Return the short form of this name, i.e. the last part.
   */
  public static String shorten(String className) {
    if (className == null) return null;
    int lastDot = className.lastIndexOf(".");
    int lastDollar = className.lastIndexOf("$");
    int last = max(lastDot,lastDollar);
    String shortName = className.substring(last + 1);
    return className.charAt(0) == '@' ? "@" + shortName : shortName;
  }
  
  public static int max(int a, int b) {
    if (a < b) return b;
    else return a;
  }
}
