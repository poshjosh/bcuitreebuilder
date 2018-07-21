package com.bc.ui.treebuilder;

import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import org.junit.Test;

/**
 *
 * @author USER
 */
public class AbstractTreeBuilderTest {
    
    private final TestBase testBase = new TestBaseImpl();
    
    /**
     * Test of build method, of class AbstractTreeBuilder.
     */
    @Test
    public void testBuild() {
testBase.log(this.getClass(), "Testing:: "+AbstractTreeBuilder.class.getName()+"#build");        
        
        final Object root = new NumbersRootNode();
        
        // Overrides AbstractTreeBuilder
        //
        TreeBuilder treeBuilder = new AbstractTreeBuilderImpl(root);
    
        TreeNode treeNode = treeBuilder.build(root, null);
        
        JTree tree = new JTree(treeNode);
        
        testBase.show(tree);
    }
    
    private class NumbersRootNode {
        @Override
        public String toString() {
            return "Numbers (1 - 3)";
        }
    }
    
    private class AbstractTreeBuilderImpl extends AbstractTreeBuilder {
        private final Object root;
        private AbstractTreeBuilderImpl(Object root) {
            this.root = root;
        }
        @Override
        protected Object[] getChildren(Object nodeObject) {
            if(nodeObject.equals(root)) {
                return new Object[]{"One", "Two", "Three"};
            }else{
                return null;
            }
        }
    }
}
