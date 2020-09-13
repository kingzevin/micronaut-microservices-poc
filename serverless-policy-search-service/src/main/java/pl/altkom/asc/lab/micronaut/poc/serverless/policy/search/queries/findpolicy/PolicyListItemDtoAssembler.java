package pl.altkom.asc.lab.micronaut.poc.serverless.policy.search.queries.findpolicy;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.search.service.api.v1.queries.findpolicy.dto.PolicyListItemDto;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.search.readmodel.PolicyView;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class PolicyListItemDtoAssembler {

    static PolicyListItemDto map(PolicyView policy) {
        return new PolicyListItemDto(
                policy.getNumber(),
                policy.getDateFrom(),
                policy.getDateTo(),
                policy.getPolicyHolder()
        );
    }
}
