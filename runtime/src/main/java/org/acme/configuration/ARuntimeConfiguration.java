package org.acme.configuration;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;

@ConfigGroup
public class ARuntimeConfiguration
{
    /**
     * make tooling super happy
     */
    @ConfigItem(name = "something")
    public String something;
}
