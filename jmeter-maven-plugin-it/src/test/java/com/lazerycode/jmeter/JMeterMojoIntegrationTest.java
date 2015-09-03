package com.lazerycode.jmeter;

import junit.framework.TestCase;
import org.apache.maven.it.Verifier;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Integration test for jmeter-maven-plugin
 */
public class JMeterMojoIntegrationTest extends TestCase {

    private String testDirectory = System.getProperty("test.directory");

    public void testJMeterMojo() throws Exception {
        File testDir = new File(testDirectory);
        Verifier verifier = new Verifier(testDir.getAbsolutePath());

        List<String> cliOptions = new ArrayList<String>();
        //produce DEBUG output in case an error occurs and one would like to take a look at the log
        cliOptions.add("-X");
        //produce execution error messages (with stacktraces)
        cliOptions.add("-e");

        verifier.setCliOptions(cliOptions);

        verifier.executeGoal("clean");
        verifier.executeGoal("verify");

        //make sure that all expected files are created, see expected-results.txt
        //also checks that Maven log does not contain "[ERROR]" elements
        verifier.verify(true);

        //log should state that test was completed
        verifier.verifyTextInLog("Completed Test: test.jmx");
    }
}