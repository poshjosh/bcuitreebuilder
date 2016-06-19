package com.bc.ui.treebuilder;

import java.io.File;
import javax.swing.JTree;

/**
 * @author USER
 */
public interface TestBase {

    File getTestDir();

    File getTestFile();

    TreeBuilderFactory getTreeBuilderFactory();

    void log(Class aClass, String fmt, Object... values);

    void log(Class aClass, Object msg);

    void log(Class aClass, Throwable t);

    void print(JTree tree);

    void show(JTree tree);
    
}
