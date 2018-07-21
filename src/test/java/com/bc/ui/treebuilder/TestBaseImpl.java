package com.bc.ui.treebuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.function.Predicate;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

/**
 * @author USER
 */
public class TestBaseImpl implements TestBase {

    public TestBaseImpl() { }
    
    @Override
    public File findAnyFile(Path startAt, Predicate<Path> test) {
        try{
            return Files.walk(startAt)
                    .filter(test)
                    .findAny()
                    .orElseThrow(() -> new FileNotFoundException("File not found in: " + startAt))
                    .toFile();
        }catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public TreeBuilderFactory getTreeBuilderFactory() {
        return new TreeBuilderFactoryImpl();
    }

    @Override
    public void show(JTree tree) {
        
        JScrollPane scrollPane  = new JScrollPane();

        scrollPane.setBounds(0, 0, 300, 300);

        scrollPane.setViewportView(tree);
        
        JOptionPane.showMessageDialog(null, scrollPane);
        
//        print(tree);
    }

    @Override
    public void print(JTree tree) {
        System.out.println("tree.getLastSelectedPathComponent(): "+tree.getLastSelectedPathComponent());
        System.out.println("tree.getAnchorSelectionPath(): "+tree.getAnchorSelectionPath());
        System.out.println("tree.getEditingPath(): "+tree.getEditingPath());
        System.out.println("tree.getExpandsSelectedPaths(): "+tree.getExpandsSelectedPaths());
        System.out.println("tree.getSelectionPath(): "+tree.getSelectionPath());
        System.out.println("tree.getSelectionPaths(): "+Arrays.deepToString(tree.getSelectionPaths()));
        System.out.println("tree.getSelectionRows(): "+Arrays.toString(tree.getSelectionRows()));
        TreeModel treeModel = tree.getModel();
        System.out.println("TreeModel: "+treeModel);
        Object root = treeModel.getRoot();
        System.out.println("treeModel.getRoot(): "+root);
        int childCount = treeModel.getChildCount(root);
        System.out.println("TreeModel.children");
        for(int i=0; i<childCount; i++) {
            System.out.println(treeModel.getChild(root, i));
        }
        if(root instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)root;
System.out.println("Breadth first enumeration");            
            print(treeNode.breadthFirstEnumeration());
System.out.println("Depth first enumeration");               
            print(treeNode.depthFirstEnumeration());
        }
    }
    
    private void print(Enumeration en) {
        while(en.hasMoreElements()) {
System.out.println(en.nextElement());            
        }
    }
    
    @Override
    public void log(Class aClass, String fmt, Object... values) {
        log(aClass, String.format(fmt, values));
    }
    @Override
    public void log(Class aClass, Object msg) {
        System.out.println(aClass.getName()+". "+msg);
    }
    @Override
    public void log(Class aClass, Throwable t) {
        t.printStackTrace();
    }
}
