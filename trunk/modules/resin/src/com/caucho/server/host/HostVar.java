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

package com.caucho.server.host;

import com.caucho.config.Config;
import com.caucho.config.ConfigException;
import com.caucho.config.inject.InjectManager;
import com.caucho.config.types.PathBuilder;
import com.caucho.el.EL;
import com.caucho.env.deploy.DeployController;
import com.caucho.env.deploy.DeployControllerAdmin;
import com.caucho.env.deploy.EnvironmentDeployController;
import com.caucho.management.server.HostMXBean;
import com.caucho.server.e_app.EarConfig;
import com.caucho.server.webapp.WebAppConfig;
import com.caucho.util.L10N;
import com.caucho.vfs.Depend;
import com.caucho.vfs.Dependency;
import com.caucho.vfs.Path;
import com.caucho.vfs.Vfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A configuration entry for a host
 */
public class HostVar
{
  HostController _hostController;
  
  HostVar(HostController controller)
  {
    _hostController = controller;
  }

  public String getName()
  {
    return _hostController.getName();
  }

  public String getHostName()
  {
    return _hostController.getHostName();
  }

  public String getUrl()
  {
    Host host = _hostController.getDeployInstance();

    if (host != null)
      return host.getURL();
    
    String hostName = _hostController.getHostName();
    
    if (hostName.equals(""))
      return "";
    else if (hostName.startsWith("http:")
        || hostName.startsWith("https:"))
      return hostName;
    else
      return "http://" + hostName;
  }

  public ArrayList<String> getRegexp()
  {
    return (ArrayList<String>) _hostController.getVariableMap().get("regexp");
  }

  public Path getRoot()
  {
    Host host = _hostController.getDeployInstance();

    if (host != null)
      return host.getWebAppContainer().getRootDirectory();
    else
      return _hostController.getRootDirectory();
  }

  /**
   * @deprecated
   */
  public Path getRootDir()
  {
    return getRoot();
  }

  /**
   * @deprecated
   */
  public Path getRootDirectory()
  {
    return getRoot();
  }

  public Path getDocumentDirectory()
  {
    Host host = _hostController.getDeployInstance();

    if (host != null)
      return host.getWebAppContainer().getDocumentDirectory();
    else
      return null;
  }

  public Path getDocDir()
  {
    return getDocumentDirectory();
  }

  public Path getWarDirectory()
  {
    Host host = _hostController.getDeployInstance();

    if (host != null)
      return host.getWebAppContainer().getWarDir();
    else
      return null;
  }

  public Path getWarDir()
  {
    return getWarDirectory();
  }

  public Path getWarExpandDirectory()
  {
    Host host = _hostController.getDeployInstance();

    if (host != null)
      return host.getWebAppContainer().getWarExpandDir();
    else
      return null;
  }

  public Path getWarExpandDir()
  {
    return getWarExpandDirectory();
  }

  @Override
  public String toString()
  {
    return "Host[" + _hostController.getId() + "]";
  }
}
