package pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.queries.getsalestrends;

import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.command.bus.QueryHandler;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.domain.LocalDateRange;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.domain.SalesTrendsQuery;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.domain.TimeAggregationUnit;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQuery;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQueryResult;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class GetSalesTrendsQueryHandler implements QueryHandler<GetSalesTrendsQueryResult, GetSalesTrendsQuery> {

    private final PolicyRepository policyRepository;

    @Override
    public GetSalesTrendsQueryResult handle(GetSalesTrendsQuery query) {
        SalesTrendsQuery.Result salesTrends = policyRepository.getSalesTrends(SalesTrendsQuery.builder()
                .filterByProductCode(query.getProductCode())
                .filterBySalesDate(LocalDateRange.between(query.getSaleDateFrom(),query.getSaleDateTo()))
                .aggregationUnit(TimeAggregationUnit.valueOf(query.getAggregationUnitCode()))
                .build());
        return GetSalesTrendsQueryResultAssembler.assemble(salesTrends);
    }
}
