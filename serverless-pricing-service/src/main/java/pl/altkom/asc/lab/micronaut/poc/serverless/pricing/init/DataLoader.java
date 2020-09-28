package pl.altkom.asc.lab.micronaut.poc.serverless.pricing.init;

import pl.altkom.asc.lab.micronaut.poc.serverless.pricing.domain.Tariffs;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class DataLoader implements ApplicationEventListener<ServerStartupEvent> {

    private final Tariffs tariffsDb;
    public static Object initializedFlag = new Object();
    @Transactional
    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        if (!tariffsDb.findByCode("HSI").isPresent()) {
            tariffsDb.save(DemoTariffsFactory.house());
        }
        
        if (!tariffsDb.findByCode("TRI").isPresent()) {
            tariffsDb.save(DemoTariffsFactory.travel());
        }

        if (!tariffsDb.findByCode("FAI").isPresent()) {
            tariffsDb.save(DemoTariffsFactory.farm());
        }

        if (!tariffsDb.findByCode("CAR").isPresent()) {
            tariffsDb.save(DemoTariffsFactory.car());
        }
        synchronized (initializedFlag){
            initializedFlag.notify();
        }
    }
}
