package pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.infrastructure.adapters.web;


import io.micronaut.http.annotation.Controller;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.command.bus.CommandBus;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.dto.PolicyDto;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.domain.PolicyDocument;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.DashboardOperations;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQueryResult;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQuery;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQueryResult;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQueryResult;

import javax.validation.constraints.NotNull;
import io.micronaut.http.annotation.Body;


@RequiredArgsConstructor
@Validated
@Controller("/dashboard")
public class DashboardController implements DashboardOperations {

    private final CommandBus bus;
    private final PolicyRepository policyRepository;

    @Override
    public GetTotalSalesQueryResult queryTotalSales(GetTotalSalesQuery query) {
        return bus.executeQuery(query);
    }

    @Override
    public GetSalesTrendsQueryResult querySalesTrends(GetSalesTrendsQuery query) {
        return bus.executeQuery(query);
    }

    @Override
    public GetAgentsSalesQueryResult queryAgentsSales(GetAgentsSalesQuery query) {
        return bus.executeQuery(query);
    }

    @Override
    public void policyRegisteredNotify(@NotNull @Body PolicyDto policy) {
        policyRepository.save(new PolicyDocument(
            policy.getNumber(),
            policy.getFrom(),
            policy.getTo(),
            policy.getPolicyHolder(),
            policy.getProductCode(),
            policy.getTotalPremium(),
            policy.getAgentLogin()
        ));
    }
}
