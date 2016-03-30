package com.bc.ui.treebuilder;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author USER
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    AbstractTreeBuilderTest.class, DOMTreeBuilderTest.class,
    FileTreeBuilderTest.class, MapTreeBuilderTest.class
})
public class TreeBuilderTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}
