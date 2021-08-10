package org.acme.reproducer.config.extension.test;

import io.quarkus.test.QuarkusUnitTest;
import org.acme.configuration.AsBuildTimeConfiguration;
import org.acme.configuration.AsRuntimeConfiguration;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import javax.inject.Inject;
import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ReproducerConfigPropertiesExtensionTest {

    // Start unit test with your extension loaded
    @RegisterExtension
    static final QuarkusUnitTest unitTest = new QuarkusUnitTest()
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class)
                    .addAsResource(new FileAsset(new File(ReproducerConfigPropertiesExtensionTest.class.getClassLoader().getResource("application.properties").getFile())), "application.properties")
            );

    @Inject
    AsBuildTimeConfiguration configuration;

    @Inject
    AsRuntimeConfiguration runtimeConfiguration;

    @Test
    public void configWasLoadedCorrectly() {
        // Write your unit tests here - see the testing extension guide https://quarkus.io/guides/writing-extensions#testing-extensions for more information

        assertThat(configuration.getNamedConfigurations(), containsInAnyOrder("test", "default"));
        final String aDefault = runtimeConfiguration.getSomething("default");
        assertThat(aDefault, is("defaultClient"));
        final String test = runtimeConfiguration.getSomething("test");
        assertThat(test, is(equalTo("testClient")));
    }
}
