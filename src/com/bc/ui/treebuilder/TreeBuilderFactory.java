package com.bc.ui.treebuilder;

import javax.swing.tree.MutableTreeNode;

/**
 * @(#)TreeBuilderFactory.java   12-Nov-2013 16:38:30
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
public abstract interface TreeBuilderFactory
{
  public static final String DOM = "com.bc.ui.tree.builder.type.dom";
  public static final String FILE = "com.bc.ui.tree.builder.type.file";
  public static final String MAP = "com.bc.ui.tree.builder.type.map";
  
  public abstract DOMTreeBuilder getDOMInstance();
  
  public abstract DOMTreeBuilder getDOMInstance(Class<? extends MutableTreeNode> paramClass);
  
  public abstract FileTreeBuilder getFileInstance();
  
  public abstract FileTreeBuilder getFileInstance(Class<? extends MutableTreeNode> paramClass);
  
  public abstract TreeBuilder getInstance(String paramString);
  
  public abstract TreeBuilder getInstance(String paramString, Class<? extends MutableTreeNode> paramClass);
  
  public abstract MapTreeBuilder getMapInstance();
  
  public abstract MapTreeBuilder getMapInstance(Class<? extends MutableTreeNode> paramClass);
  
  public abstract String[] getTypeNames();
}
