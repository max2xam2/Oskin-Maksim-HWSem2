package org.app.hwsem2mts.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@Endpoint(id = "custom-endpoint-first")
public class firstCustomEndpoint {
  @ReadOperation
  public UUID randomUUID() {
    return UUID.randomUUID();
  }
}