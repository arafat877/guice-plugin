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

/**
 * Classes for representing and managing modules in the user's code.
 * 
 * <p>
 * Modules in the user's code are managed by the
 * {@link com.google.inject.tools.suite.module.ModuleManager} which is injected as a
 * singleton.
 * 
 * <p>
 * Modules are represented by the
 * {@link com.google.inject.tools.suite.module.ModuleRepresentation} class and module
 * contexts, i.e. guice contexts that injectors are created in are represented
 * by {@link com.google.inject.tools.suite.module.ModuleContextRepresentation}.
 * ModuleRepresentations are created for each module in the user's code.
 * ModuleContextRepresentations are created for each context the user specifies
 * to use the plugin for.
 * 
 * @author Darren Creutz (dcreutz@gmail.com)
 */

package com.google.inject.tools.suite.module;