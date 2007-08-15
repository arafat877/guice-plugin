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

package com.google.inject.tools.ideplugin.eclipse;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import com.google.inject.Inject;
import com.google.inject.tools.ideplugin.Messenger;
import com.google.inject.tools.ideplugin.ProgressHandler;

/**
 * Eclipse implementation of the {@link ProgressHandler}.
 * 
 * @author Darren Creutz <dcreutz@gmail.com>
 */
public class EclipseProgressHandler implements ProgressHandler {
  private final Messenger messenger;
  private final List<ProgressStep> steps;
  
  @Inject
  public EclipseProgressHandler(Messenger messenger) {
    this.messenger = messenger;
    this.steps = new ArrayList<ProgressStep>();
  }
  
  /**
   * (non-Javadoc)
   * @see com.google.inject.tools.ideplugin.ProgressHandler#isCancelled()
   */
  public boolean isCancelled() {
    return false;
  }
  
  /**
   * (non-Javadoc)
   * @see com.google.inject.tools.ideplugin.ProgressHandler#step(java.lang.String, com.google.inject.tools.ideplugin.code.CodeRunner)
   */
  public void step(ProgressStep step) {
    steps.add(step);
  }
  
  public void go(String label, boolean backgroundAutomatically) {
    Job job = new ProgressHandlerJob(label);
    //Job job = new LongJob();
    job.setUser(!backgroundAutomatically);
    job.schedule();
  }
  
  private class ProgressHandlerJob extends Job {
    private final String label;
    public ProgressHandlerJob(String label) {
      super(label);
      this.label = label;
    }
    @Override
    protected IStatus run(IProgressMonitor monitor) {
      monitor.beginTask(label, steps.size());
      for (ProgressStep step : steps) {
        if (monitor.isCanceled()) {
          step.cancel();
        } else {
          monitor.setTaskName(step.label());
          step.run();
          while (!monitor.isCanceled() && !step.isDone()) {
            try {
              Thread.sleep(100);
            } catch (InterruptedException exception) {
              EclipseProgressHandler.this.messenger.log("Job interrupted " + exception.toString());
            }
          }
          if (monitor.isCanceled()) {
            step.cancel();
            monitor.done();
          } else {
            monitor.worked(1);
            step.complete();
          }
        }
      }
      monitor.done();
      if (monitor.isCanceled()) return Status.CANCEL_STATUS;
      else return Status.OK_STATUS;
    }
  }
  
  //TODO: remove this
  //for testing...
  /*private class LongJob extends Job {
    public LongJob() {
      super("Long Job");
    }
    @Override
    protected IStatus run(IProgressMonitor monitor) {
      monitor.beginTask("Long Job", 10);
      for (int i=0; i<10; i++) {
        try {
          Thread.sleep(2000);
        } catch (Exception e) {}
        monitor.worked(1);
      }
      monitor.done();
      if (monitor.isCanceled()) return Status.CANCEL_STATUS;
      else return Status.OK_STATUS;
    }
  }*/
}
