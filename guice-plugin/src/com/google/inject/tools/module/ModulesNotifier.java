/**
 * Copyright (C) 2007 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.inject.tools.module;

import java.util.Set;

import com.google.inject.tools.JavaManager;

/**
 * Responsible for listening to changes in the user's code involving {@link com.google.inject.Module}s.
 * IDE specific implementations should notify the ModuleManager when changes occur by
 * creating {@link ModuleRepresentation} objects and passing them to the manager.
 * 
 * @author Darren Creutz <dcreutz@gmail.com>
 */
public interface ModulesNotifier {
  /**
   * Find the modules in the user's code by name.  The {@link ModuleManager} will call this method
   * when it is created and if it ever needs to refresh its list.
   * 
   * @return the module names
   */
  public Set<String> findModules();
  
  /**
   * Notify the listener that the project changed at the user's request.
   * 
   * @param project the new project
   */
  public void projectChanged(JavaManager project);
  
  /**
   * Search the user's code for changes to the modules and notify the {@link ModuleManager} of any found.
   */
  public void findChanges();
}