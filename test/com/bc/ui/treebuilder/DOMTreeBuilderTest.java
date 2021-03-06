package com.bc.ui.treebuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @author USER
 */
public class DOMTreeBuilderTest {
    
    private final TestBase testBase = new TestBaseImpl();
    /**
     * Test of build method, of class DOMTreeBuilder.
     */
    @Test
    public void testBuild() {
testBase.log(this.getClass(), "Testing:: "+DOMTreeBuilder.class.getName()+"#build");        
        File dir = Paths.get(System.getProperty("user.home"),
                "/Documents/NetBeansProjects/looseboxes/web").toFile();
        
        Document doc = load(new File(dir, "/resources/welcome.xml"));
        
        DOMTreeBuilder treeBuilder = testBase.getTreeBuilderFactory().getDOMInstance();
        
        TreeNode treeNode = treeBuilder.build(doc, null);
        
        JTree tree = new JTree(treeNode);
        
        testBase.show(tree);
    }

    private Document load(File file) {
        Document doc;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            doc = docBuilder.parse(file);
            testBase.log(this.getClass(), "Doc URL: %1s, Base URI: %2s, File: %3s", 
                    doc.getDocumentURI(), doc.getBaseURI(), file);                    
        }catch (SAXException | IOException | ParserConfigurationException e) {
            testBase.log(this.getClass(), e);
            doc = null;
        }
        return doc;
    }
}
