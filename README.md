# bcuitreebuilder
A lightweight java library (8 classes) for building javax.swing.tree.TreeNodes from Files, DOMs, Maps etc

# FileTreeBuilder
        TreeBuilderFactory treeBuilderFactory = new TreeBuilderFactoryImpl();

        // Get the directory whose content will be used to build the tree
        //
        File dir = new File(System.getProperty("user.home")+"/Documents");

        TreeBuilder<File> fileTreeBuilder = treeBuilderFactory.getInstance(TreeBuilderFactory.FILE);
        // This also works
        // FileTreeBuilder fileTreeBuilder = treeBuilderFactory..getFileInstance(); 

        Filter<File> fileFilter = new Filter<File>() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith(".docx");
            }
        };

        TreeNode fileRootNode = fileTreeBuilder.build(dir, fileFilter);

        JTree fileTree = new JTree(fileRootNode);

        // Display the JTree
        //
        JScrollPane scrollPane  = new JScrollPane(fileTree);
        scrollPane.setBounds(0, 0, 300, 300);
        JOptionPane.showMessageDialog(null, scrollPane);

# DOMTreeBuilder
        TreeBuilderFactory treeBuilderFactory = new TreeBuilderFactoryImpl();

        // Load the document whose nodes will be used to build the tree 
        //
        Document doc = loadDocument(new File(System.getProperty("user.home")+"/Documents/Desktop/welcome.xml"));

        DOMTreeBuilder domTreeBuilder = treeBuilderFactory.getDOMInstance();

        Filter<Node> nodeFilter = null; // May be null

        TreeNode docRootNode = domTreeBuilder.build(doc, nodeFilter);

        JTree documentTree = new JTree(docRootNode);

        // Display the JTree
        //
        JScrollPane scrollPane  = new JScrollPane(documentTree);
        scrollPane.setBounds(0, 0, 300, 300);
        JOptionPane.showMessageDialog(null, scrollPane);

# MapTreeBuilder
        TreeBuilderFactory treeBuilderFactory = new TreeBuilderFactoryImpl();

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
        JScrollPane scrollPane  = new JScrollPane(mapTree);
        scrollPane.setBounds(0, 0, 300, 300);
        JOptionPane.showMessageDialog(null, scrollPane);
