package com.bc.ui.treebuilder;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import org.junit.Test;

/**
 * @author USER
 */
public class MapTreeBuilderTest {

    private TestBase testBase = new TestBaseImpl();
    
    /**
     * Test of build method, of class MapTreeBuilder.
     */
    @Test
    public void testBuild() {
testBase.log(this.getClass(), "Testing:: "+MapTreeBuilder.class.getName()+"#build");        
        MapTreeBuilder mapTreeBuilder = testBase.getTreeBuilderFactory().getMapInstance();
        
        final HashMap map = new HashMap();
        map.put("boolean", Boolean.TRUE);
        map.put("number", 100);
        map.put("List", new String[]{"1", "2", "3"});
        HashMap grandChildren = new HashMap();
        grandChildren.put("grandChild", "I am a grand child");
        map.put("hasChildren", grandChildren);
        
        Map.Entry rootEntry = mapTreeBuilder.createRootEntry(map);
        
        TreeNode treeNode = mapTreeBuilder.build(rootEntry, null);

        JTree tree = new JTree(treeNode);
        
        testBase.show(tree);
    }
}
