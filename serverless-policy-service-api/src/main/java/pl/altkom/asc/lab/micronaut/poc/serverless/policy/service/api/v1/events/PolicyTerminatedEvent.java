package pl.altkom.asc.lab.micronaut.poc.serverless.policy.service.api.v1.events;

import pl.altkom.asc.lab.micronaut.poc.serverless.policy.service.api.v1.events.dto.PolicyDto;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Introspected
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyTerminatedEvent {
    public PolicyDto policy;
}
