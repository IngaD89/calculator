package org.example;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.example.verticle.CalcuVerticle;

public class App {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new CalcuVerticle(),
                new DeploymentOptions()
                        .setConfig(new JsonObject()
                                .put("port", 8080))

                );
    }
}
