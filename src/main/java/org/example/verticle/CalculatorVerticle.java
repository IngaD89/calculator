package org.example.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.openapi.RouterBuilder;
import org.example.routerContext.RContext;


public class CalculatorVerticle extends AbstractVerticle {

    RContext rc = new RContext();

    @Override
    public void start() {
        RouterBuilder.create(vertx, "src/main/resources/openapi.yaml")
                .onFailure(Throwable::printStackTrace)

                .onSuccess(routerBuilder -> {
                    routerBuilder.getOpenAPI().getOpenAPI();

                    routerBuilder
                            .operation("suma")
                            .handler(routingContext -> rc.suma(routingContext));
                    routerBuilder
                            .operation("resta")
                            .handler(routingContext -> rc.resta(routingContext));
                    routerBuilder
                            .operation("getHistories")
                            .handler(routingContext -> rc.history(routingContext));
                    routerBuilder
                            .operation("getHistory")
                            .handler(routingContext -> rc.getHistoryById(routingContext));


                    Router router = routerBuilder.createRouter();
                    HttpServer server = vertx.createHttpServer();
                    server
                            .requestHandler(router)
                            .listen(config()
                                    .getInteger("port"));
                });

    }


}