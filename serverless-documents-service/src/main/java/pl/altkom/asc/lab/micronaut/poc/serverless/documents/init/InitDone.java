package pl.altkom.asc.lab.micronaut.poc.serverless.documents.init;

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
    System.out.println("onApplicationEvent");
    synchronized(initializedFlag){
      initializedFlag.notify();
    }
  }
}
