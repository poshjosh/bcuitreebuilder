package com.bc.ui.treebuilder;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import org.junit.Test;

/**
 * @author USER
 */
public class FileTreeBuilderTest {
    
    private final TestBase testBase = new TestBaseImpl();
    
    @Test
    public void testBuild() {       
testBase.log(this.getClass(), "Testing:: "+FileTreeBuilder.class.getName()+"#build");
        
        // Using TreeBuilderFactory
        //
        TreeBuilderFactory treeBuilderFactory = testBase.getTreeBuilderFactory();

        final Path userHomePath = Paths.get(System.getProperty("user.home"));
        
        final File webXmlFile = testBase.findAnyFile(userHomePath, (path) -> path.getFileName().toString().equals("web.xml"));
        
        // FileTreeBuilder
        //
        File dir = webXmlFile.getParentFile().getParentFile();
        
        final TreeBuilder<File> treeBuilder = treeBuilderFactory.getInstance(TreeBuilderFactory.FILE);
        
        TreeNode treeNode = treeBuilder.build(dir, null);
        
        JTree tree = new JTree(treeNode);
        
        testBase.show(tree);

        // FileTreeBuilder with custom behaviour for displaying node
        //
        treeNode = treeBuilder.build(dir, (fname) -> {
            return fname.isDirectory() || fname.getName().endsWith(".jsp");
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
