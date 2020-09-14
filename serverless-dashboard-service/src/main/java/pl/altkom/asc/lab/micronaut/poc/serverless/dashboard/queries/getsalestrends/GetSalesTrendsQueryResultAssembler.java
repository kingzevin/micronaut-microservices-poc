package pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.queries.getsalestrends;

import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.domain.SalesTrendsQuery;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQueryResult;
import pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.api.v1.queries.getsalestrendsquery.dto.PeriodSalesDto;

import java.util.ArrayList;

public class GetSalesTrendsQueryResultAssembler {

    public static GetSalesTrendsQueryResult assemble(SalesTrendsQuery.Result salesTrands) {
        GetSalesTrendsQueryResult result = new GetSalesTrendsQueryResult(new ArrayList<>());
        salesTrands.getPeriodSales().forEach(periodSales ->
                result.getPeriodSales().add(new PeriodSalesDto(
                        periodSales.getPeriodDate(),
                        periodSales.getPeriod(),
                        periodSales.getSales().getPoliciesCount(),
                        periodSales.getSales().getPremiumAmount()
                )));
        return result;
    }
}
