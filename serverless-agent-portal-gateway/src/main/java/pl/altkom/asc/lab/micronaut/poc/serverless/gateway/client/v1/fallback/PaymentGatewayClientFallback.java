package pl.altkom.asc.lab.micronaut.poc.serverless.gateway.client.v1.fallback;

import io.micronaut.retry.annotation.Fallback;
import pl.altkom.asc.lab.micronaut.poc.serverless.gateway.client.v1.PaymentGatewayClient;
import pl.altkom.asc.lab.micronaut.poc.payment.service.api.v1.PolicyAccountBalanceDto;
import pl.altkom.asc.lab.micronaut.poc.payment.service.api.v1.PolicyAccountDto;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Singleton
@Fallback
public class PaymentGatewayClientFallback implements PaymentGatewayClient {
    @Override
    public Collection<PolicyAccountDto> accounts() {
        return Collections.emptyList();
    }

    @Override
    public PolicyAccountBalanceDto accountBalance(String accountNumber) {
        return new PolicyAccountBalanceDto(accountNumber, null, null, new Date(), new Date());
    }
}
