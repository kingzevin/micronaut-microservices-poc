package pl.altkom.asc.lab.micronaut.poc.serverless.gateway.client.v1;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import pl.altkom.asc.lab.micronaut.poc.payment.service.api.v1.operations.PaymentOperations;

@Client(id = "payment-service")
@Retryable(attempts = "2", delay = "2s")
public interface PaymentGatewayClient extends PaymentOperations {

}
