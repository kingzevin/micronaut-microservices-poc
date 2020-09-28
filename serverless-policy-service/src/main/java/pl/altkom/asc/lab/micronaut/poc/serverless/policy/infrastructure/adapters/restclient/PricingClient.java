package pl.altkom.asc.lab.micronaut.poc.serverless.policy.infrastructure.adapters.restclient;

import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import pl.altkom.asc.lab.micronaut.poc.serverless.pricing.service.api.v1.commands.calculateprice.CalculatePriceCommand;
import pl.altkom.asc.lab.micronaut.poc.serverless.pricing.service.api.v1.commands.calculateprice.CalculatePriceResult;
import pl.altkom.asc.lab.micronaut.poc.serverless.pricing.service.api.v1.PricingOperations;

@Client("https://172.17.0.1/api/v1/web/guest/poc/serverless-pricing-service")
public interface PricingClient extends PricingOperations {
    @Override
    @Post("/pricing/calculate")
    CalculatePriceResult calculatePrice(CalculatePriceCommand cmd);
}
