package com;

import com.client.journeyTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class journeySuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for journey");
    suite.addTestSuite(journeyTest.class);
    return suite;
  }
}
