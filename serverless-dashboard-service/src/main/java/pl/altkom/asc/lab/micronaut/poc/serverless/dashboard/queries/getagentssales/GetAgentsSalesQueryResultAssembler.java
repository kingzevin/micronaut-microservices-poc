package pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.queries.getagentssales;

import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.domain.AgentSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQueryResult;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.getagentssalesquery.dto.SalesDto;

import java.util.HashMap;

public class GetAgentsSalesQueryResultAssembler {

    public static GetAgentsSalesQueryResult assemble(AgentSalesQuery.Result agentsSales) {
        GetAgentsSalesQueryResult result = new GetAgentsSalesQueryResult(new HashMap<>());
        agentsSales.getPerAgentTotal().forEach((agent,sales) ->
            result.getPerAgentTotal().put(agent, new SalesDto(sales.getPoliciesCount(), sales.getPremiumAmount()))
        );
        return result;
    }
}
