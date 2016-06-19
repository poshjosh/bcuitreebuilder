package com.bc.ui.treebuilder;

import java.io.File;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import org.junit.Test;

/**
 * @author USER
 */
public class FileTreeBuilderTest {
    
    private TestBase testBase = new TestBaseImpl();
    
    @Test
    public void testBuild() {       
testBase.log(this.getClass(), "Testing:: "+FileTreeBuilder.class.getName()+"#build");
        
        // Using TreeBuilderFactory
        //
        TreeBuilderFactory treeBuilderFactory = testBase.getTreeBuilderFactory();

        // FileTreeBuilder
        //
        File dir = testBase.getTestDir();
        
        final TreeBuilder<File> treeBuilder = treeBuilderFactory.getInstance(TreeBuilderFactory.FILE);
        
        TreeNode treeNode = treeBuilder.build(dir, null);
        
        JTree tree = new JTree(treeNode);
        
        testBase.show(tree);

        // FileTreeBuilder with custom behaviour for displaying node
        //
        treeNode = treeBuilder.build(dir, new Filter<File>() {
            @Override
            public boolean accept(File e) {
                return e.isDirectory() || e.getName().endsWith(".jsp");
            }
        });
        
        // Custom behaviour for displaying node
        //
        tree = new JTree(treeNode){
            @Override
            public String convertValueToText(Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                return ((FileTreeBuilder)treeBuilder).convertValueToText(value, selected, expanded, leaf, row, hasFocus);
            }
        };
        
        testBase.show(tree);
    }
}
