/*
 * Copyright (c) 1998-2010 Caucho Technology -- all rights reserved
 *
 * This file is part of Resin(R) Open Source
 *
 * Each copy or derived work must preserve the copyright notice and this
 * notice unmodified.
 *
 * Resin Open Source is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * Resin Open Source is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, or any warranty
 * of NON-INFRINGEMENT.  See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Resin Open Source; if not, write to the
 *
 *   Free Software Foundation, Inc.
 *   59 Temple Place, Suite 330
 *   Boston, MA 02111-1307  USA
 *
 * @author Scott Ferguson
 */

package com.caucho.boot;

import com.caucho.lifecycle.Lifecycle;
import com.caucho.util.*;

import java.lang.reflect.*;
import java.net.*;
import java.util.logging.*;

/**
 * Thread responsible for the Resin restart capability, managing and
 * restarting the WatchdogProcess.
 *
 * Each WatchdogProcess corresponds to a single Resin instantiation.  When
 * Resin exits, the WatchdogProcess completes, and WatchdogTask will
 * create a new one.
 */
class WatchdogTask implements Runnable
{
  private static final L10N L = new L10N(WatchdogTask.class);
  private static final Logger log
    = Logger.getLogger(WatchdogTask.class.getName());

  private final Watchdog _watchdog;
  
  private final Lifecycle _lifecycle = new Lifecycle();

  private WatchdogProcess _process;

  WatchdogTask(Watchdog watchdog)
  {
    _watchdog = watchdog;

    if (watchdog == null)
      throw new NullPointerException();
  }

  /**
   * True if the Resin server is currently active.
   */
  boolean isActive()
  {
    return _lifecycle.isActive();
  }

  /**
   * Returns the state name.
   */
  String getState()
  {
    return _lifecycle.getStateName();
  }

  /**
   * Returns the pid of the current Resin process, when the pid is
   * available through JNI.
   */
  int getPid()
  {
    WatchdogProcess process = _process;
    if (process != null)
      return process.getPid();
    else
      return 0;
  }

  /**
   * Starts management of the watchdog process
   */
  public void start()
  {
    if (! _lifecycle.toActive())
      return;

    ThreadPool.getCurrent().schedule(this);
  }

  /**
   * Stops the watchdog process.  Once stopped, the WatchdogProcess will
   * not be reused.
   */
  public void stop()
  {
    if (! _lifecycle.toDestroy())
      return;

    WatchdogProcess process = _process;
    _process = null;
    
    if (process != null) {
      process.stop();

      process.waitForExit();
    }
  }

  /**
   * Main thread watching over the health of the Resin instances.
   */
  public void run()
  {
    try {
      Thread thread = Thread.currentThread();
      thread.setName("watchdog-" + _watchdog.getId());
      
      int i = 0;
      long retry = Long.MAX_VALUE;
    
      while (_lifecycle.isActive() && i++ < retry) {
	String id = String.valueOf(i);

	_watchdog.notifyTaskStarted();

	log.info(_watchdog + " starting");
	
	_process = new WatchdogProcess(id, _watchdog);
	
	try {
	  _process.run();
	} catch (Exception e) {
	  log.log(Level.WARNING, e.toString(), e);
	} finally {
	  WatchdogProcess process = _process;
	  _process = null;

	  if (process != null)
	    process.kill();
	}
      }
	
      log.info(_watchdog + " stopped");
    } catch (Exception e) {
      log.log(Level.WARNING, e.toString(), e);
    } finally {
      _lifecycle.toDestroy();

      _watchdog.completeTask(this);
    }
  }
  
  /**
   * kills the task
   */
  void kill()
  {
    _lifecycle.toDestroy();
    
    WatchdogProcess process = _process;
    _process = null;
    
    if (process != null)
      process.kill();
  }

  @Override
  public String toString()
  {
    return getClass().getSimpleName() + "[" + _watchdog + "]";
  }
}
