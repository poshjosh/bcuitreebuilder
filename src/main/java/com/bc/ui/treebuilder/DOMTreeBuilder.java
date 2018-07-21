package com.bc.ui.treebuilder;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @(#)DOMTreeBuilder.java   12-Nov-2013 16:43:17
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
public class DOMTreeBuilder
  extends AbstractTreeBuilder<Node>
{
  public DOMTreeBuilder() {}
  
  public DOMTreeBuilder(Class<? extends MutableTreeNode> treeNodeClass)
  {
    super(treeNodeClass);
  }
  
  @Override
  public Node[] getChildren(Node node)
  {
    NodeList list = node.getChildNodes();
    Node[] children = new Node[list.getLength()];
    for (int i = 0; i < list.getLength(); i++) {
      children[i] = list.item(i);
    }
    return children;
  }
  
  @Override
  protected MutableTreeNode getTreeNode(final Node node)
  {
    DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(node)
    {
      @Override
      public String toString() {
        return node.hasChildNodes() ? node.getNodeName() : DOMTreeBuilder.getString(node);
      }
    };
    return treeNode;
  }

  @Override
  protected String convertUserObjectToText(Node userObject, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
  {
    return (!selected) || (!expanded) || (!leaf) ? userObject.getNodeName() : getString(userObject);
  }
  
  private static String getString(Node node) {
    return node.getNodeName() + (node.getTextContent() == null ? "" : new StringBuilder().append(' ').append(node.getTextContent()).toString());
  }
}
