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
 * Responsible for tracking the modules which should be run when resolving bindings and injections.
 * The {@link ModulesNotifier} notifies the ModuleManager when these change.
 * 
 * @author Darren Creutz <dcreutz@gmail.com>
 */
public interface ModuleManager {
  public static class NoProjectException extends RuntimeException {
    private final ModuleManager moduleManager;
    public NoProjectException(ModuleManager moduleManager) {
      this.moduleManager = moduleManager;
    }
    @Override
    public String toString() {
      return "No project selected in ModuleManager: " + moduleManager;
    }
  }
  
  /**
   * Notify the ModuleManager that a module has been added by the user.
   * 
   * @param module the module added
   * @param createContext true if the manager should create a new context for this module
   */
  public void addModule(ModuleRepresentation module, boolean createContext) throws NoProjectException;
  
  /**
   * Notify the ModuleManager that a module has been removed by the user.
   * 
   * @param module the module removed
   */
  public void removeModule(ModuleRepresentation module) throws NoProjectException;
  
  /**
   * Notify the ModuleManager that a module has been added by the user.
   * 
   * @param moduleName the name of the module added
   * @param createContext true if the manager should create a new context for this module
   */
  public void addModule(String moduleName, boolean createContext) throws NoProjectException;
  
  /**
   * Notify the manager that a new module name is available.
   * This should only be called the {@link ModulesNotifier}.
   */
  public void initModuleName(String moduleName);
  
  /**
   * Notify the ModuleManager that a module has been removed by the user.
   * 
   * @param moduleName the name of the module removed
   */
  public void removeModule(String moduleName) throws NoProjectException;
  
  /**
   * Notify the ModuleManager that all modules for the current project should be cleared from memory.
   */
  public void clearModules();
  
  /**
   * Notify the ModuleManager to clear all modules for the given project.
   * 
   * @param whichProject the project to clear modules from
   */
  public void clearModules(JavaManager whichProject);
  
  /**
   * Get all modules that have been added for the current project.
   * 
   * @return the {@link ModuleContextRepresentation}s
   */
  public Set<ModuleRepresentation> getModules();
  
  /**
   * Get all modules that have been added to the given project.
   * 
   * @param whichProject the project
   * @return the modules
   */
  public Set<ModuleRepresentation> getModules(JavaManager whichProject);
  
  /**
   * Add a {@link ModuleContextRepresentation} to the manager.
   * 
   * @param moduleContext the module context
   * @param active true if the context should be marked as active
   */
  public void addModuleContext(ModuleContextRepresentation moduleContext, boolean active) throws NoProjectException;
  
  /**
   * Remove a {@link ModuleContextRepresentation} from the manager.
   * 
   * @param moduleContext the module context
   */
  public void removeModuleContext(ModuleContextRepresentation moduleContext) throws NoProjectException;
  
  /**
   * Clear all {@link ModuleContextRepresentation}s from the manager for the current project.
   */
  public void clearModuleContexts();
  
  /**
   * Clear the {@link ModuleContextRepresentation}s from the manager for the given project.
   * 
   * @param whichProject the project to clear contexts from
   */
  public void clearModuleContexts(JavaManager whichProject);
  
  /**
   * Return a set of all {@link ModuleContextRepresentation}s the manager has for the current project.
   * 
   * @return the module contexts
   */
  public Set<ModuleContextRepresentation> getModuleContexts();
  
  /**
   * Return a set of all {@link ModuleContextRepresentation}s for the given project.
   * 
   * @param whichProject the project
   * @return the modules
   */
  public Set<ModuleContextRepresentation> getModuleContexts(JavaManager whichProject);
  
  /**
   * Return the active contexts.
   */
  public Set<ModuleContextRepresentation> getActiveModuleContexts();
  
  /**
   * Return the active contexts for the given project.
   */
  public Set<ModuleContextRepresentation> getActiveModuleContexts(JavaManager whichProject);
  
  /**
   * Notify the manager that a module has changed; it will tell the contexts.
   * 
   * @param module the module
   */
  public void moduleChanged(String module);
  
  /**
   * Ask the Manager to update the module list to be the modules for the given project.
   * 
   * @param waitFor true if the current thread should wait for the update
   * @return true if the update succeeded (false if the user cancelled the operation)
   */
  public boolean updateModules(JavaManager javaProject, boolean waitFor);
  
  /**
   * Return the current {@link JavaManager}.
   */
  public JavaManager getCurrentProject();
  
  /**
   * Tell the module manager to run modules automatically as needed.
   */
  public void setRunAutomatically(boolean run);
  
  /**
   * True if newly created modules should be activated by default.
   */
  public boolean activateModulesByDefault();
  
  /**
   * Set default activation of modules.
   */
  public void setActivateModulesByDefault(boolean activateByDefault);
  
  /**
   * Activate a module context.
   */
  public void activateModuleContext(ModuleContextRepresentation moduleContext);
  
  /**
   * Deactivate a module context.
   */
  public void deactivateModuleContext(ModuleContextRepresentation moduleContext); 
}