package org.acme.configuration;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;

@ConfigGroup
public class ABuildTimeConfiguration
{
    /**
     * make tooling super happy
     */
    @ConfigItem(name = ConfigItem.PARENT)
    public String something;

}
