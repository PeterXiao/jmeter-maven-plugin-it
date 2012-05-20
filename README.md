# jmeter-maven-plugin-it

Integration test for the jmeter-maven-plugin.

The [Maven Verifier][1] is used to run the test.
It contains a `pom.xml` with a full configuration for the [JMeter Maven Plugin][2].

At the beginning the test, a [Jetty HTTP Server][3] is started, and a dummy webapp with an `index.html` is deployed.
After that, the JMeter testplan `test.jmx` requests the `index.html` 10 times.
After the JMeter test ends, the Jetty server is shut down again.

[Maven Verifier][1] then checks whether the plugin was executed without problems.


Run the test in module `jmeter-maven-plugin-it` with `mvn clean verify`.
The Parent POM must be installed into local Maven repository in order for the test to work.
Running `mvn clean install` in the workspace root will first install the parent and then invoke the test.

[1]:    http://maven.apache.org/shared/maven-verifier/        "Maven Verifier Component"
[2]:    http://jmeter.lazerycode.com                          "JMeter Maven Plugin"
[3]:    http://jetty.codehaus.org/jetty/                      "Jetty HTTP Server"