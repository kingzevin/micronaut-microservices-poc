package pl.altkom.asc.lab.micronaut.poc.serverless.policy.infrastructure.adapters.bus;

import pl.altkom.asc.lab.micronaut.poc.serverless.command.bus.MicronautCommandBus;
import pl.altkom.asc.lab.micronaut.poc.serverless.command.bus.Registry;

import javax.inject.Singleton;

@Singleton
public class PolicyCommandBus extends MicronautCommandBus {
    public PolicyCommandBus(Registry registry) {
        super(registry);
    }
}
