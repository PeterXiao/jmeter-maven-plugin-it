# jmeter-maven-plugin-it

Integration test for the jmeter-maven-plugin.

The [Maven Verifier][1] is used to run the test.
It contains a `pom.xml` with a full configuration for the [JMeter Maven Plugin][2].

At the beginning the test, a [Jetty HTTP Server][3] is started, and a dummy webapp with an `index.html` is deployed.
After that, the JMeter testplan `test.jmx` requests the `index.html` 10 times.
After the JMeter test ends, the Jetty server is shut down again.

[Maven Verifier][1] then checks whether
 * the plugin was executed successfully
 * the maven log shows any ERRORS
 * the maven log contains text that indicates that the jmeter-maven-plugin finished correctly
 * that all files listed in `expected-results.txt` are present afterwards

if any of the above is negative, the test fails.

In order to make version, dependency and plugin management as easy as possible, both the `jmeter-maven-plugin-it` module and the enclosed `jmeter-maven-plugin-it-run` module (the module that runs the actual test) inherit from `jmeter-maven-plugin-it-parent`.


[1]:    http://maven.apache.org/shared/maven-verifier/        "Maven Verifier Component"
[2]:    http://jmeter.lazerycode.com                          "JMeter Maven Plugin"
[3]:    http://jetty.codehaus.org/jetty/                      "Jetty HTTP Server"