package org.acme;

import io.quarkus.runtime.annotations.Recorder;
import org.acme.bean.Greeting;
import org.acme.configuration.AsRuntimeConfiguration;

import java.util.function.Supplier;

@Recorder
public class MyRecorder {

    public Supplier<Greeting> createGreeting(String buildTimeName, AsRuntimeConfiguration configuration){
        return () -> new Greeting(configuration.getSomething(buildTimeName));
    }
}
