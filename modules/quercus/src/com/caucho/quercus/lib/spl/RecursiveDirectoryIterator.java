/*
 * Copyright (c) 1998-2014 Caucho Technology -- all rights reserved
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
 * @author Nam Nguyen
 */

package com.caucho.quercus.lib.spl;

import com.caucho.quercus.annotation.Optional;
import com.caucho.quercus.env.Env;
import com.caucho.quercus.env.LongValue;
import com.caucho.quercus.env.QuercusClass;
import com.caucho.quercus.env.StringValue;
import com.caucho.quercus.env.Value;
import com.caucho.vfs.Path;

public class RecursiveDirectoryIterator
  extends FilesystemIterator
  implements RecursiveIterator
{
  private QuercusClass _quercusClass;
  public RecursiveDirectoryIterator(Env env,
                                    StringValue fileName,
                                    @Optional("-1") int flags)
  {
    super(env, fileName, flags);
    _quercusClass = env.getCallingClass();
  }

  protected RecursiveDirectoryIterator(QuercusClass quercusClass, Path parent, Path path, String fileName, int flags)
  {
    super(parent, path, fileName, flags);
    _quercusClass = quercusClass;
  }

  //
  // RecursiveIterator
  //

  @Override
  public boolean hasChildren(Env env)
  {
    SplFileInfo current = getCurrent(env);

    if (current == null) {
      return false;
    }

    String fileName = current.getFilename(env);

    if (".".equals(fileName) || "..".equals(fileName)) {
      return false;
    }

    return current.isDir(env);
  }

  @Override
  public Value getChildren(Env env)
  {
    SplFileInfo info = getCurrent(env);

    return _quercusClass.callNew(env,
        env.createString(info.getRawPath().toString()), LongValue.create(getFlags()));
  }
  
  /**
   * (PHP 5 &gt;= 5.1.0)<br/>
   * Get sub path
   * @link http://php.net/manual/en/recursivedirectoryiterator.getsubpath.php
   * @return string The sub path (sub directory).
   */
  public String getSubPath (Env env) 
  {
    return "";
  }

  /**
   * (PHP 5 &gt;= 5.1.0)<br/>
   * Get sub path and name
   * @link http://php.net/manual/en/recursivedirectoryiterator.getsubpathname.php
   * @return string The sub path (sub directory) and filename.
   */
  public String getSubPathname (Env env)
  {
    Value current = current(env);
    Value result = current.callMethod(env, env.createString("getFilename"));
    return result == null ? null : result.toString();
  }
  
  /*
  @Override
  protected SplFileInfo createCurrent(Env env, Path path)
  {
    if (path.isDirectory()) {
      return new RecursiveDirectoryIterator(path, getFlags());
    }
    else {
      return super.createCurrent(env, path);
    }
  }
  */
}
