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

import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.tools.ideplugin.JavaProject;
import com.google.inject.tools.ideplugin.ModulesSource;
import com.google.inject.tools.ideplugin.AbstractSourceImpl;
import com.google.inject.tools.suite.Messenger;
import com.google.inject.tools.suite.ProgressHandler.ProgressMonitor;

class IntellijModulesListener extends AbstractSourceImpl implements ModulesSource {
  @Inject
  public IntellijModulesListener(Messenger messenger) {
    super(messenger);
  }
  
  @Override
  protected Set<String> locate(JavaProject javaProject, ProgressMonitor monitor)
      throws Throwable {
    return null;
  }
  
  public boolean isListeningForChanges() {
    return false;
  }
  
  public void listenForChanges(boolean listenForChanges) {
  }
}
