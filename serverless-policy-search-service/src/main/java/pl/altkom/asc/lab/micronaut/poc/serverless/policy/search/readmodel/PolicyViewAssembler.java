package pl.altkom.asc.lab.micronaut.poc.serverless.policy.search.readmodel;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.dto.PolicyDto;

//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public final class PolicyViewAssembler {

    // to public
    public static PolicyView map(PolicyDto policy) {
        return PolicyView.builder()
                .number(policy.getNumber())
                .dateFrom(policy.getFrom())
                .dateTo(policy.getTo())
                .policyHolder(policy.getPolicyHolder())
                .build();
    }
}
