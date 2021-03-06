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

package com.google.inject.tools.ideplugin.intellij;

import com.google.inject.tools.suite.ProgressHandler;

class IntellijProgressHandler implements ProgressHandler {
  
  public void go(String label, boolean backgroundAutomatically) {
    
  }
  
  public void go(String label, boolean backgroundAutomatically, boolean cancelThread) {
    
  }
  
  public boolean isCancelled() {
    return false;
  }
  
  public void step(ProgressStep step) {
    
  }
  
  public void waitFor() {
    
  }
  
  public boolean isDone() {
    return true;
  }
  
  public void executeAfter(Runnable executeAfter) {
    
  }
}
