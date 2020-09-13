package pl.altkom.asc.lab.micronaut.poc.serverless.policy.search.service.api.v1;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.reactivex.Maybe;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.search.service.api.v1.queries.findpolicy.FindPolicyQueryResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.dto.PolicyDto;

import javax.validation.constraints.NotNull;

public interface PolicySearchOperations {

    @Get
    Maybe<FindPolicyQueryResult> policies(@QueryValue("q") String queryText);

    @Post("/terminate")
    void policyTerminatedNotify(@Body @NotNull PolicyDto policy);

    @Post("/register")
    void policyRegisteredNotify(@Body @NotNull PolicyDto policy);


}
