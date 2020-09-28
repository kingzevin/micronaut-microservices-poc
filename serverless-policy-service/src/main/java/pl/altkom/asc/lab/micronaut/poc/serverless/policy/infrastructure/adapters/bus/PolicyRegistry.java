package pl.altkom.asc.lab.micronaut.poc.serverless.policy.infrastructure.adapters.bus;

import io.micronaut.context.ApplicationContext;
import pl.altkom.asc.lab.micronaut.poc.serverless.command.bus.Registry;

import javax.inject.Singleton;

@Singleton
public class PolicyRegistry extends Registry {
    public PolicyRegistry(ApplicationContext applicationContext) {
        super(applicationContext);
    }
}
