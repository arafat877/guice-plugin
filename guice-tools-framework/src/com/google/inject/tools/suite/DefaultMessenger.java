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

package com.google.inject.tools.suite;

import com.google.inject.Singleton;

/**
 * Implementation of the {@link Messenger} that does nothing.
 * 
 * @author Darren Creutz (dcreutz@gmail.com)
 */
@Singleton
class DefaultMessenger implements Messenger {
  public void display(String message) {
  }

  public void logException(String label, Throwable throwable) {
  }

  public void logMessage(String message) {
  }
  
  public void logCodeRunnerMessage(String message) {
  }
  
  public void logCodeRunnerException(String label, Throwable throwable) {
  }
}
