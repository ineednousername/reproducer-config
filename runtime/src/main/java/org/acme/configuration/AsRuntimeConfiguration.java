package org.acme.configuration;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

import java.util.Map;

@ConfigRoot(name = "reproducer", phase = ConfigPhase.RUN_TIME)
public class AsRuntimeConfiguration {

    /**
     * make tooling super happy
     */
    @ConfigItem
    public ARuntimeConfiguration client;

    /**
     * make tooling super happy
     */
    @ConfigItem(name = ConfigItem.PARENT)
    public Map<String, ANamedRuntimeConfiguration> namedClients;

    public String getSomething(String buildTimeName) {
        if ("default".equals(buildTimeName))
            return client.something;
        return extractSomething(namedClients.get(buildTimeName));
    }

    private String extractSomething(ANamedRuntimeConfiguration configuration){
        if(configuration !=null)
           return configuration.client.something;
        return null;
    }

    @ConfigGroup
    public static class ANamedRuntimeConfiguration {

        /**
         * make tooling super happy
         */
        @ConfigItem
        public ARuntimeConfiguration client;
    }
}
