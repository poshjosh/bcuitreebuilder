package com.bc.ui.treebuilder;

import java.io.File;
import java.nio.file.Path;
import java.util.function.Predicate;
import javax.swing.JTree;

/**
 * @author USER
 */
public interface TestBase {
    
    File findAnyFile(Path startAt, Predicate<Path> test);

    TreeBuilderFactory getTreeBuilderFactory();

    void log(Class aClass, String fmt, Object... values);

    void log(Class aClass, Object msg);

    void log(Class aClass, Throwable t);

    void print(JTree tree);

    void show(JTree tree);
    
}
