package org.acme;

import io.quarkus.runtime.annotations.Recorder;
import org.acme.bean.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

@Recorder
public class MyRecorder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyRecorder.class);

    public Supplier<Greeting> createGreeting(String buildTimeName, String something) {
        return () -> {
            LOGGER.info("created " + buildTimeName + " with greeting " + something);
            return new Greeting(something);
        };
    }
}
