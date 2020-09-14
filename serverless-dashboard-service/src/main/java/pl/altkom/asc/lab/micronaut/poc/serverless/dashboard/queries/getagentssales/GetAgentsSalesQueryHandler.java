package pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.queries.getagentssales;

import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.command.bus.QueryHandler;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.domain.AgentSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.domain.LocalDateRange;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQueryResult;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class GetAgentsSalesQueryHandler implements QueryHandler<GetAgentsSalesQueryResult, GetAgentsSalesQuery> {

    private final PolicyRepository policyRepository;

    @Override
    public GetAgentsSalesQueryResult handle(GetAgentsSalesQuery query) {
        AgentSalesQuery.Result agentsSales = policyRepository.getAgentSales(AgentSalesQuery.builder()
                .filterByAgentLogin(query.getAgentLogin())
                .filterByProductCode(query.getProductCode())
                .filterBySalesDate(LocalDateRange.between(query.getSaleDateFrom(),query.getSaleDateTo()))
                .build());
        return GetAgentsSalesQueryResultAssembler.assemble(agentsSales);
    }
}
