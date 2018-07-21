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
public interface TreeBuilderFactory
{
  String DOM = "com.bc.ui.tree.builder.type.dom";
  String FILE = "com.bc.ui.tree.builder.type.file";
  String MAP = "com.bc.ui.tree.builder.type.map";
  
  DOMTreeBuilder getDOMInstance();
  
  DOMTreeBuilder getDOMInstance(Class<? extends MutableTreeNode> paramClass);
  
  FileTreeBuilder getFileInstance();
  
  FileTreeBuilder getFileInstance(Class<? extends MutableTreeNode> paramClass);
  
  TreeBuilder getInstance(String paramString);
  
  TreeBuilder getInstance(String paramString, Class<? extends MutableTreeNode> paramClass);
  
  MapTreeBuilder getMapInstance();
  
  MapTreeBuilder getMapInstance(Class<? extends MutableTreeNode> paramClass);
  
  String[] getTypeNames();
}
