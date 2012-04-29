package com.lazerycode.jmeter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;
import org.junit.Test;

/**
 * Integration test for jmeter-maven-plugin
 */
public class JMeterMojoIntegrationTest extends TestCase {

  private static final String GROUP_ID = "com.lazerycode.jmeter";
  private static final String VERSION = "1.5.0";
  private static final String ARTIFACT_ID_IT = "jmeter-maven-plugin-it";
  private static final String ARTIFACT_ID_IT_RUN = "jmeter-maven-plugin-it-run";
  private static final String PACKAGING = "pom";

  public void testJMeterMojo() throws Exception {

      Verifier verifier;

      // The testdir is computed from the location of this file.
      File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/jmeter-maven-plugin-it-run");

      /**
       * We must first make sure that any artifact created
       * by this test has been removed from the local
       * repository. Failing to do this could cause
       * unstable test results. Fortunately, the verifier
       * makes it easy to do this.
       */
      verifier = new Verifier( testDir.getAbsolutePath() );
      verifier.deleteArtifact( GROUP_ID, ARTIFACT_ID_IT, VERSION, PACKAGING );
      verifier.deleteArtifact( GROUP_ID, ARTIFACT_ID_IT_RUN, VERSION, PACKAGING );

      /**
       * The Command Line Options (CLI) are passed to the
       * verifier as a list. This is handy for things like
       * redefining the local repository if needed. In
       * this case, we use the -N flag so that Maven won't
       * recurse.
       */
      List<String> cliOptions = new ArrayList<String>();
      cliOptions.add( "-N" );
      verifier.setCliOptions( cliOptions );

      //call "mvn clean verify" for jmeter-maven-plugin
      verifier.executeGoal( "clean" );
      verifier.executeGoal( "verify" );

      /**
       * This is the simplest way to check a build
       * succeeded. It is also the simplest way to create
       * an IT test: make the build pass when the test
       * should pass, and make the build fail when the
       * test should fail. There are other methods
       * supported by the verifier. They can be seen here:
       * http://maven.apache.org/shared/maven-verifier/apidocs/index.html
       */
      verifier.verifyErrorFreeLog();

      //TODO: test that all necessary files are created etc.

      /**
       * Reset the streams before executing the verifier
       * again.
       */
      //verifier.resetStreams();
    }
}