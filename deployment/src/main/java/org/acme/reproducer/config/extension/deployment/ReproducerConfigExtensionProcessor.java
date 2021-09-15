package org.acme.reproducer.config.extension.deployment;

import io.quarkus.arc.deployment.SyntheticBeanBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import org.acme.MyRecorder;
import org.acme.bean.Greeting;
import org.acme.configuration.AsBuildTimeConfiguration;
import org.jboss.jandex.DotName;

import javax.enterprise.context.ApplicationScoped;

class ReproducerConfigExtensionProcessor {

    private static final String FEATURE = "reproducer-config-extension";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    @Record(ExecutionTime.RUNTIME_INIT)
    void createClients(AsBuildTimeConfiguration build, MyRecorder recorder, BuildProducer<SyntheticBeanBuildItem> beans) {
        build.getNamedConfigurations().forEach(clientName -> {
            SyntheticBeanBuildItem.ExtendedBeanConfigurator configurator = SyntheticBeanBuildItem
                    .configure(Greeting.class)
                    .name(Greeting.class.getName() + "_" + clientName)
                    .named(clientName)
                    .addType(DotName.createSimple(Greeting.class.getName()))
                    .scope(ApplicationScoped.class)
                    .setRuntimeInit()
                    .supplier(recorder.createGreeting(clientName, "default".equals(clientName)? build.client.something: build.namedClients.get(clientName).client.something));
            beans.produce(configurator.done());
        });

    }
}
