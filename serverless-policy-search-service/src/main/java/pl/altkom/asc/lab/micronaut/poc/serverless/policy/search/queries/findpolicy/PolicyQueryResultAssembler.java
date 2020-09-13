package pl.altkom.asc.lab.micronaut.poc.serverless.policy.search.queries.findpolicy;

import pl.altkom.asc.lab.micronaut.poc.serverless.policy.search.readmodel.PolicyView;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.search.service.api.v1.queries.findpolicy.FindPolicyQueryResult;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.search.service.api.v1.queries.findpolicy.dto.PolicyListItemDto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class PolicyQueryResultAssembler {

    static FindPolicyQueryResult constructResult(List<PolicyView> policies) {
        return new FindPolicyQueryResult(
                policies.stream()
                        .map(PolicyListItemDtoAssembler::map)
                        .sorted(Comparator.comparing(PolicyListItemDto::getDateFrom, Comparator.nullsLast(Comparator.reverseOrder())))
                        .collect(Collectors.toList())
        );
    }
}