package pl.altkom.asc.lab.micronaut.poc.serverless.gateway.client.v1;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.PolicySearchOperations;

@Client(id = "policy-search-service")
@Retryable(attempts = "2", delay = "2s")
public interface PolicySearchGatewayClient extends PolicySearchOperations {
}
