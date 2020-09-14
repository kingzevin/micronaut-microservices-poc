package pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQueryResult;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQuery;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQueryResult;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQueryResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.dto.PolicyDto;

import javax.validation.constraints.NotNull;

public interface DashboardOperations {

    @Post("/totalsales")
    GetTotalSalesQueryResult queryTotalSales(@Body GetTotalSalesQuery query);

    @Post("/trends")
    GetSalesTrendsQueryResult querySalesTrends(@Body GetSalesTrendsQuery query);

    @Post("/agentssales")
    GetAgentsSalesQueryResult queryAgentsSales(@Body GetAgentsSalesQuery query);

    @Post("/register")
    void policyRegisteredNotify(@Body @NotNull PolicyDto policy);
}
