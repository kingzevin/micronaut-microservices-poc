package pl.altkom.asc.lab.micronaut.poc.serverless.product.service.domain;

import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.List;

public interface Products {

    Single<Product> add(Product product);

    Single<List<Product>> findAll();

    Maybe<Product> findOne(String productCode);
}
