package pl.altkom.asc.lab.micronaut.poc.serverless.gateway.client.v1;


import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.DashboardOperations;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQueryResult;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQueryResult;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQueryResult;

@Client(id = "dashboard-service")
@Retryable(attempts = "2", delay = "2s")
public interface DashboardGatewayClient extends DashboardOperations {

    @Override
    @Post("/totalsales")
    GetTotalSalesQueryResult queryTotalSales(@Body GetTotalSalesQuery query);

    @Override
    @Post("/trends")
    GetSalesTrendsQueryResult querySalesTrends(@Body GetSalesTrendsQuery query);

    @Override
    @Post("/agentssales")
    GetAgentsSalesQueryResult queryAgentsSales(@Body GetAgentsSalesQuery query);

}
