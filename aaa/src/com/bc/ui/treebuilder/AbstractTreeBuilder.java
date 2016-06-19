package com.bc.ui.treebuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

/**
 * @(#)AbstractTreeBuilder.java   10-Dec-2013 19:01:53
 *
 * Copyright 2011 NUROX Ltd, Inc. All rights reserved.
 * NUROX Ltd PROPRIETARY/CONFIDENTIAL. Use is subject to license 
 * terms found at http://www.looseboxes.com/legal/licenses/software.html
 */
/**
 * @author   chinomso bassey ikwuagwu
 * @version  2.0
 * @param <E>
 * @since    2.0
 */
public abstract class AbstractTreeBuilder<E>
  implements TreeBuilder<E>
{
  final Class<? extends MutableTreeNode> treeNodeClass;
  
  protected AbstractTreeBuilder()
  {
    this(DefaultMutableTreeNode.class);
  }
  
  protected AbstractTreeBuilder(Class<? extends MutableTreeNode> treeNodeClass) {
    if (treeNodeClass == null) {
      this.treeNodeClass = DefaultMutableTreeNode.class;
    } else {
      this.treeNodeClass = treeNodeClass;
    }
  }
  
  protected abstract E[] getChildren(E paramE);
  
  @Override
  public MutableTreeNode build(E nodeObject, Filter<E> filter)
  {
    if (nodeObject == null) {
      throw new NullPointerException();
    }
    
    if ((filter != null) && (!filter.accept(nodeObject))) {
      return null;
    }
    
    MutableTreeNode treeNode = getTreeNode(nodeObject);
    
    E[] children = getChildren(nodeObject);
    
    if ((children != null) && (children.length != 0))
    {
      for (E child : children)
      {
        MutableTreeNode childNode = build(child, filter);
        
        if (childNode != null) {
          add(treeNode, childNode);
        }
      }
    }
    
    return treeNode;
  }
  

  public String convertValueToText(Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
  {
    if ((value instanceof DefaultMutableTreeNode))
    {
      E userObject = (E)((DefaultMutableTreeNode)value).getUserObject();
      
      return convertUserObjectToText(userObject, selected, expanded, leaf, row, hasFocus);
    }
    
    // This behaviour was copied direct from javax.swing.JTree.convertUserObjectToText
    //
    if (value != null) {
      String sValue = value.toString();
      if (sValue != null) {
        return sValue;
      }
    }
    return "";
  }

  protected String convertUserObjectToText(E userObject, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
  {
    return userObject.toString();
  }
  
  /**
   * Convenience method simply copied from 
   * {@link javax.swing.tree.DefaultMutableTreeNode#add(javax.swing.tree.MutableTreeNode)}
   * @param parent The MutableTreeNode to which the newChild will be added
   * @param newChild The MutableTreeNode to add to the parent
   */
  protected void add(MutableTreeNode parent, MutableTreeNode newChild)
  {
    if ((newChild != null) && (newChild.getParent() == this)) {
      parent.insert(newChild, parent.getChildCount() - 1);
    } else {
      parent.insert(newChild, parent.getChildCount());
    }
  }
  
  protected MutableTreeNode getTreeNode(E e) {
    if (this.treeNodeClass == DefaultMutableTreeNode.class) {
      return new DefaultMutableTreeNode(e);
    }
    MutableTreeNode treeNode = instantiateNewTreeNode(this.treeNodeClass);
    treeNode.setUserObject(e);
    return treeNode;
  }

  protected MutableTreeNode instantiateNewTreeNode(Class<? extends MutableTreeNode> treeNodeClass)
  {
    try
    {
      Constructor<? extends MutableTreeNode> constructor = treeNodeClass.getConstructor(new Class[0]);
      
      return (MutableTreeNode)constructor.newInstance(new Object[0]);

    }
    catch (NoSuchMethodException|SecurityException|InstantiationException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e)
    {
      String msg = String.format("Call to no-argument constructor failed on: %1s", new Object[] { treeNodeClass });
      

      Logger.getLogger(getClass().getName()).log(Level.WARNING, msg, e);
    }
    return null;
  }
  
  public Class<? extends MutableTreeNode> getTreeNodeClass()
  {
    return this.treeNodeClass;
  }
}
