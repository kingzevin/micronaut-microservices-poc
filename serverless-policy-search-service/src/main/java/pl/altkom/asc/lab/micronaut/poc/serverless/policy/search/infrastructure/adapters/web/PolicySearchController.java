package pl.altkom.asc.lab.micronaut.poc.serverless.policy.search.infrastructure.adapters.web;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import io.reactivex.Maybe;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.command.bus.CommandBus;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.dto.PolicyDto;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.search.readmodel.PolicyView;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.search.readmodel.PolicyViewAssembler;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.search.readmodel.PolicyViewRepository;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.search.service.api.v1.PolicySearchOperations;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.search.service.api.v1.queries.findpolicy.FindPolicyQuery;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.search.service.api.v1.queries.findpolicy.FindPolicyQueryResult;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Controller("/policies")
public class PolicySearchController implements PolicySearchOperations {

    private final CommandBus bus;
    @Inject
    private PolicyViewRepository policyViewRepository;
    void saveMappedPolicy(PolicyDto policy) {
        PolicyView view = PolicyViewAssembler.map(policy);
        policyViewRepository.save(view);
    }

    @Override
    public Maybe<FindPolicyQueryResult> policies(String queryText) {
        return bus.executeQuery(new FindPolicyQuery(queryText));
    }

    @Override
    public void policyTerminatedNotify(@Body @NotNull PolicyDto policy){
        saveMappedPolicy(policy);
    }

    @Override
    public void policyRegisteredNotify(@Body @NotNull PolicyDto policy){
        saveMappedPolicy(policy);
    }

}
