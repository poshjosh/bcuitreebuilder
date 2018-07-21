package com.bc.ui.treebuilder;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

/**
 * @(#)MapTreeBuilder.java   24-Dec-2013 23:32:00
 *
 * Copyright 2011 NUROX Ltd, Inc. All rights reserved.
 * NUROX Ltd PROPRIETARY/CONFIDENTIAL. Use is subject to license 
 * terms found at http://www.looseboxes.com/legal/licenses/software.html
 */
/**
 * <p>
 * MapTreeBuilder uses a Map.Entry as user object.<br/>
 * <b>Example:</b>
 * </p>
 * <p>
 * <tt>String jsonText = {"String":"Some Text","Map":{"key_0":"val_0","key_1":"val_1"}}</tt>
 * </p>
 * Given the above json input, The root node is a Map.Entry wit key <tt>""</tt>
 * and value <tt>jsonText</tt>. The first child in the root node is a Map.Entry 
 * with key <tt>String</tt> and value <tt>Some Text</tt>. While the second child 
 * in the root node is a Map.Entry with key <tt>Map</tt> and value 
 * <tt>{"key_0":"val_0","key_1":"val_1"}</tt>
 * @author   chinomso bassey ikwuagwu
 * @version  2.0
 * @since    2.0
 */
public class MapTreeBuilder extends AbstractTreeBuilder<Map.Entry>
{
  public MapTreeBuilder() {}
  
  public MapTreeBuilder(Class<? extends MutableTreeNode> treeNodeClass)
  {
    super(treeNodeClass);
  }
  
  public MutableTreeNode build(Map m) {
    return build(this.createRootEntry(m), null);
  }

  public Map.Entry getRootNode(Object key, Map data) {
    if (key == null) {
      key = "";
    }
    return createEntry(key, data);
  }
  
  @Override
  protected Map.Entry[] getChildren(Map.Entry entry)
  {
    Object entryValue = entry.getValue();
    
    Map.Entry[] output;
    if (entryValue == null)
    {
      output = null;
    } else { 
      if ((entryValue instanceof Map))
      {
        Set<Map.Entry> entrySet = ((Map)entryValue).entrySet();
        
        output = (Map.Entry[])entrySet.toArray(new Map.Entry[0]);
      }
      else if ((entryValue instanceof Collection))
      {
        Collection c = (Collection)entryValue;
        
        output = new Map.Entry[c.size()];
        
        Iterator iter = c.iterator();
        
        for (int i = 0; iter.hasNext(); i++)
        {
          Object key = iter.next();
          
          output[i] = createEntry(key, null);
        }
        
      }
      else if ((entryValue instanceof Object[]))
      {
        Object[] arr = (Object[])entryValue;
        
        output = new Map.Entry[arr.length];
        
        for (int i = 0; i < arr.length; i++)
        {
          Object key = arr[i];
          
          output[i] = createEntry(key, null);
        }
      }
      else
      {
        output = new Map.Entry[1];
        
        output[0] = createEntry(entryValue, null);
      }
    }
    return output;
  }
  
  @Override
  protected MutableTreeNode getTreeNode(final Map.Entry entry)
  {
    DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(entry)
    {
      @Override
      public String toString() {
        return entry.getKey().toString();
      }
    };
    return treeNode;
  }
  


  @Override
  protected String convertUserObjectToText(Map.Entry userObject, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
  {
    return userObject.getKey().toString();
  }

  /**
   * Create an immutable {@link java.util.Map.Entry} from the input {@link java.util.Map}
   * The input Map is used as the entry's value while the entry's key is simply 
   * empty text
   * @param data
   * @return An immutable {@link java.util.Map.Entry} created from the input {@link java.util.Map}
   */
  public Map.Entry createRootEntry(Map data)
  {
    return createEntry("", data);
  }

  /**
   * Create an immutable {@link java.util.Map.Entry} from the input key-val pair
   * @param key
   * @param val
   * @return An immutable {@link java.util.Map.Entry} created from the input key-val pair
   */
  public Map.Entry createEntry(final Object key, final Object val)
  {
    return new Map.Entry()
    {
      @Override
      public Object getKey() {
        return key;
      }
      
      @Override
      public Object getValue() {
        return val;
      }
      
      @Override
      public Object setValue(Object value) {
        throw new UnsupportedOperationException("Not supported");
      }
    };
  }
}
