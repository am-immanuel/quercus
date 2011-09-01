/*
 * Copyright (c) 1998-2011 Caucho Technology -- all rights reserved
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
 * @author Emil Ong
 */

package com.caucho.xtpdoc;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class IncludeDefun extends Section {
  private String _name;
  private Defun _defun;

  public IncludeDefun(Document document)
  {
    super(document);
  }

  public void setName(String name)
  {
    _name = name;
  }

  public String getTitle()
  {
    if (getDefun() != null)
      return getDefun().getTitle();

    return super.getTitle();
  }

  public String getHref()
  {
    if (getDefun() != null)
      return getDefun().getHref();

    return super.getHref();
  }

  private Defun getDefun()
  {
    if (_defun == null) {
      ReferenceDocument referenceDocument 
        = getDocument().getReferenceDocument();

      if (referenceDocument != null)
        _defun = referenceDocument.getDefun(_name);
    }

    return _defun;
  }

  @Override
  public void writeHtml(XMLStreamWriter out)
    throws XMLStreamException
  {
    if (getDefun() != null)
      getDefun().writeHtml(out);
  }

  @Override
  public void writeLaTeX(PrintWriter out)
    throws IOException
  {
    // XXX
  }

  @Override
  public void writeLaTeXEnclosed(PrintWriter out)
    throws IOException
  {
    writeLaTeX(out);
  }

  @Override
  public void writeLaTeXTop(PrintWriter out)
    throws IOException
  {
    writeLaTeX(out);
  }
}