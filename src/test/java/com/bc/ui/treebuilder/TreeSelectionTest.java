package com.bc.ui.treebuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * @author Josh
 */
public class TreeSelectionTest {
    
    public static void main(String [] args) {
        
        List<String> selection = new TreeSelectionTest().promptSelection(Arrays.asList("Good", "Bad", "Numbers"));
        
System.out.println(selection);
    }    
        
    private List<String> promptSelection(final Collection<String> options) {
        
        final String parent = "All Orphan Configs";
        AbstractTreeBuilder<String> treeBuilder = new AbstractTreeBuilder<String>(){
            @Override
            protected String[] getChildren(String nodeObject) {
                if(nodeObject.equals(parent)) {
                    return options.toArray(new String[0]);
                }else if(nodeObject.equals("Good")){
                    return new String[]{"God", "Holy Spirit", "Jesus"};
                }else if(nodeObject.equals("Bad")) {
                    return new String[]{"Satan", "Demons", "Fallen Angels"};
                }else if(nodeObject.equals("Numbers")) {
                    return new String[]{"Positive", "Negative"};
                }else if(nodeObject.equals("Positive")) {
                    return new String[]{"1", "2", "3"};
                }else if(nodeObject.equals("Negative")) {
                    return new String[]{"-1", "-2", "-3"};
                }else{
                    return null;
                }
           }
        };
        
        TreeNode treeNode = treeBuilder.build(parent, null);
        
        JScrollPane scroller = new JScrollPane();
        
        scroller.setSize(300, 400);
        
        JTree tree = new JTree(treeNode);
        
        scroller.setViewportView(tree);
        
        JOptionPane.showMessageDialog(null, scroller);
        
        List<String> selection;
        
        final int [] selectionRows = tree.getSelectionRows();
        
System.out.println("Selection rows: "+(selectionRows==null?null:Arrays.toString(selectionRows)));

        if(selectionRows != null && selectionRows.length > 0) {
            
            selection = new ArrayList(selectionRows.length);
            
            for(int selectionRow:selectionRows) {
                
                TreePath treePath = tree.getPathForRow(selectionRow);

System.out.println("Row: "+selectionRow+", treePath: "+(treePath));

                final String configName = treePath.getLastPathComponent().toString();
                
                selection.add(configName);
            }
        }else{
            
            selection = Collections.EMPTY_LIST;
        }
        
        return selection;
    }
}
