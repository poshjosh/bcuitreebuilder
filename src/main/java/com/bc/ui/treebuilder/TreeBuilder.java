package com.bc.ui.treebuilder;

import java.util.function.Predicate;
import javax.swing.tree.MutableTreeNode;

/**
 * @(#)TreeBuilder.java   12-Nov-2013 16:19:07
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
public interface TreeBuilder<E> {
    
    MutableTreeNode build(E paramE, Predicate<E> paramFilter);
}
