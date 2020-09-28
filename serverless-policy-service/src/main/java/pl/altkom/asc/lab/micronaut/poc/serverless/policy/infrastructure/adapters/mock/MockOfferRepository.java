package pl.altkom.asc.lab.micronaut.poc.serverless.policy.infrastructure.adapters.mock;

import pl.altkom.asc.lab.micronaut.poc.serverless.policy.domain.Offer;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.domain.OfferRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;

@Replaces(OfferRepository.class)
@Requires(env = Environment.TEST)
@Singleton
public class MockOfferRepository implements OfferRepository {

    private Map<String, Offer> map = new ConcurrentHashMap<>();

    @Transactional
    @Override
    public Offer save(Offer offer) {
        System.out.println("hrerererer");
        map.put(offer.getNumber(), offer);
        return offer;
    }

    @Transactional
    @Override
    public Offer getByNumber(String number) {
        System.out.println("hrerererer");
        return map.get(number);
    }

}
