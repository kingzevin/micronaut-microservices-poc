package pl.altkom.asc.lab.micronaut.poc.serverless.policy.search.init;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import lombok.RequiredArgsConstructor;
import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class InitDone implements ApplicationEventListener<ServerStartupEvent> {

  public static Object initializedFlag = new Object();
  @Override
  public void onApplicationEvent(ServerStartupEvent serverStartupEvent) {
    synchronized(initializedFlag){
      initializedFlag.notify();
    }
  }
}
