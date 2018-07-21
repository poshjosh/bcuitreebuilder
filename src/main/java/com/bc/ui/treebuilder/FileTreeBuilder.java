package com.bc.ui.treebuilder;

import java.io.File;
import java.io.FileFilter;
import javax.swing.tree.MutableTreeNode;

/**
 * @(#)FileTreeBuilder.java   12-Nov-2013 16:20:10
 *
 * Copyright 2011 NUROX Ltd, Inc. All rights reserved.
 * NUROX Ltd PROPRIETARY/CONFIDENTIAL. Use is subject to license 
 * terms found at http://www.looseboxes.com/legal/licenses/software.html
 */
/**
 * @author   chinomso bassey ikwuagwu
 * @version  2.0
 * @since    2.0
 */
public class FileTreeBuilder extends AbstractTreeBuilder<File>
{
  public FileTreeBuilder() {}
  
  public FileTreeBuilder(Class<? extends MutableTreeNode> treeNodeClass)
  {
    super(treeNodeClass);
  }
  
  @Override
  public File[] getChildren(File file)
  {
    return file.listFiles((FileFilter)null);
  }
  
  @Override
  protected String convertUserObjectToText(File userObject, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
  {
    return (selected) || (expanded) ? userObject.getPath() : userObject.getName();
  }
}
