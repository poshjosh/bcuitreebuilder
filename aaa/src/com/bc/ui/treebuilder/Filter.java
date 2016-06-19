package com.bc.ui.treebuilder;

/**
 * @(#)Filter.java   12-Nov-2013 16:25:12
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
public abstract interface Filter<E>
{
  public abstract boolean accept(E paramE);
}
