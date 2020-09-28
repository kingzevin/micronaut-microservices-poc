package pl.altkom.asc.lab.micronaut.poc.serverless.policy.queries.getpolicydetails;

import pl.altkom.asc.lab.micronaut.poc.serverless.command.bus.QueryHandler;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.domain.Policy;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.service.api.v1.queries.getpolicydetails.GetPolicyDetailsQuery;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.service.api.v1.queries.getpolicydetails.GetPolicyDetailsQueryResult;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.shared.exceptions.BusinessException;

import java.util.Optional;

import javax.inject.Singleton;

import io.micronaut.transaction.annotation.ReadOnly;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class GetPolicyDetailsQueryHandler implements QueryHandler<GetPolicyDetailsQueryResult, GetPolicyDetailsQuery> {

    private final PolicyRepository policyRepository;

    @ReadOnly
    @Override
    public GetPolicyDetailsQueryResult handle(GetPolicyDetailsQuery query) {
        Optional<Policy> policyOpt = policyRepository.findByNumber(query.getNumber());
        if (!policyOpt.isPresent())
            throw new BusinessException("POLICY NOT FOUND");

        return new GetPolicyDetailsQueryResult(PolicyDetailsDtoAssembler.map(policyOpt.get()));
    }
}
