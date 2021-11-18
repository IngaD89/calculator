package org.example;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.example.verticle.CalculatorVerticle;



public class App {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new CalculatorVerticle(),
                new DeploymentOptions()
                        .setConfig(new JsonObject()
                                .put("port", 8080))

                );
    }
}
