package org.acme.configuration;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

import java.util.*;

@ConfigRoot(name = "reproducer", phase = ConfigPhase.BUILD_AND_RUN_TIME_FIXED)
public class AsBuildTimeConfiguration {

    /**
     * make tooling super happy
     */
    @ConfigItem
    public ABuildTimeConfiguration client;

    /**
     * make tooling super happy
     */
    @ConfigItem(name = ConfigItem.PARENT)
    public Map<String, ANamedBuildTimeConfiguration> namedClients;

    public List<String> getNamedConfigurations() {
        final ArrayList<String> namedClients = new ArrayList<>(this.namedClients.keySet());
        Optional.ofNullable(client).ifPresent(c -> namedClients.add("default"));
        return namedClients;
    }

    @ConfigGroup
    public static class ANamedBuildTimeConfiguration {

        /**
         * make tooling super happy
         */
        @ConfigItem
        public ABuildTimeConfiguration client;
    }
}
