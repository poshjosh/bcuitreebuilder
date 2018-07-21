package com.bc.ui.treebuilder;

import java.util.Arrays;
import javax.swing.tree.MutableTreeNode;

public class TreeBuilderFactoryImpl
  implements TreeBuilderFactory
{
  @Override
  public String[] getTypeNames()
  {
    return new String[] { DOM, FILE, MAP };
  }
  
  @Override
  public TreeBuilder getInstance(String type)
  {
    return getInstance(type, null);
  }
  
  @Override
  public TreeBuilder getInstance(String type, Class<? extends MutableTreeNode> treeNodeClass)
  {
    TreeBuilder treeBuilder;
    switch (type) {
    case DOM: 
      treeBuilder = getDOMInstance(treeNodeClass);
      break;
    case FILE: 
      treeBuilder = getFileInstance(treeNodeClass);
      break;
    case MAP: 
      treeBuilder = getMapInstance(treeNodeClass);
      break;
    default: 
      throw new UnsupportedOperationException("Unexpected type: " + type + ", expected any of: " + Arrays.toString(getTypeNames()));
    }
    return treeBuilder;
  }
  
  @Override
  public FileTreeBuilder getFileInstance()
  {
    return new FileTreeBuilder();
  }
  
  @Override
  public FileTreeBuilder getFileInstance(Class<? extends MutableTreeNode> treeNodeClass)
  {
    return new FileTreeBuilder(treeNodeClass);
  }
  
  @Override
  public DOMTreeBuilder getDOMInstance()
  {
    return new DOMTreeBuilder();
  }
  
  @Override
  public DOMTreeBuilder getDOMInstance(Class<? extends MutableTreeNode> treeNodeClass)
  {
    return new DOMTreeBuilder(treeNodeClass);
  }
  
  @Override
  public MapTreeBuilder getMapInstance()
  {
    return new MapTreeBuilder();
  }
  
  @Override
  public MapTreeBuilder getMapInstance(Class<? extends MutableTreeNode> treeNodeClass)
  {
    return new MapTreeBuilder(treeNodeClass);
  }
}
