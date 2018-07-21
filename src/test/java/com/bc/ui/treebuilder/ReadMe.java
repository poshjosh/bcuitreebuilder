package com.bc.ui.treebuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * @author poshjosh
 */
public class ReadMe {

    public static void main(String [] args) {

        TreeBuilderFactory treeBuilderFactory = new TreeBuilderFactoryImpl();

        // For displaying each JTree
        //
        JScrollPane scrollPane  = new JScrollPane();
        scrollPane.setBounds(0, 0, 300, 300);
        
        final JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setDialogTitle("Select HTML/XML file");
        final int selection = chooser.showDialog(null, "View Document Structure");
        if(selection == JFileChooser.APPROVE_OPTION) {
           
            // DocumentTreeBuilder 
            //
            Document doc = loadDocument(chooser.getSelectedFile());

            DOMTreeBuilder domTreeBuilder = treeBuilderFactory.getDOMInstance();

            Predicate<Node> nodeFilter = null; // May be null

            TreeNode docRootNode = domTreeBuilder.build(doc, nodeFilter);

            JTree documentTree = new JTree(docRootNode);

            // Display the JTree
            //
            scrollPane.setViewportView(documentTree);
            JOptionPane.showMessageDialog(null, scrollPane);
        }
        
        // FileTreeBuilder
        //
        File dir = new File(System.getProperty("user.home")+"/Documents");

        TreeBuilder<File> fileTreeBuilder = treeBuilderFactory.getInstance(TreeBuilderFactory.FILE);
        // This also works
        //FileTreeBuilder fileTreeBuilder = treeBuilderFactory.getFileInstance(); 

        final Predicate<File> fileFilter = (f) -> {
            return f.isDirectory() || f.getName().endsWith(".docx");
        };

        TreeNode fileRootNode = fileTreeBuilder.build(dir, fileFilter);

        JTree fileTree = new JTree(fileRootNode);

        // Display the JTree
        //
        scrollPane.setViewportView(fileTree);
        JOptionPane.showMessageDialog(null, scrollPane);

        
        // MapTreeBuilder
        //
        MapTreeBuilder mapTreeBuilder = treeBuilderFactory.getMapInstance();

        final HashMap map = new HashMap();
        map.put("boolean", Boolean.TRUE);
        map.put("number", 100);
        map.put("List", new String[]{"1", "2", "3"});
        HashMap grandChildren = new HashMap();
        grandChildren.put("grandChild", "I am a grand child");
        map.put("hasChildren", grandChildren);

        Map.Entry rootEntry = mapTreeBuilder.createRootEntry(map);

        TreeNode mapRootNode = mapTreeBuilder.build(rootEntry, null);

        JTree mapTree = new JTree(mapRootNode);

        // Display the JTree
        //
        scrollPane.setViewportView(mapTree);
        JOptionPane.showMessageDialog(null, scrollPane);
    }

    private static Document loadDocument(File file) {
        Document doc;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            doc = docBuilder.parse(file);
        }catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
            doc = null;
        }
        return doc;
    }
}
