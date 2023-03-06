# jacoco-webclient-bug

Short repro for Kotlin/WebClient bug in JaCoCo

We discovered that JaCoCo appears to mistakenly mark certain Kotlin `suspend` functions 
as not fully covered by tests, but only when they call `WebClient.awaitBody()`. This 
sample shows this behaviour. When built and run with `mvn verify`, the JaCoCo coverage 
report will show that `doLocalWork()` is fully covered, but `doWebWork()` is not.
