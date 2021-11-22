package org.example.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.validation.RequestParameter;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;
import org.example.services.CalculatorService;


public class CalculatorVerticle extends AbstractVerticle {


    @Override
    public void start() {
        RouterBuilder.create(vertx, "src/main/resources/openapi-spec.yaml")
                .onFailure(Throwable::printStackTrace)

                .onSuccess(routerBuilder -> {
                    routerBuilder.getOpenAPI().getOpenAPI();

                    routerBuilder
                            .operation("suma")
                            .handler(this::suma);
                    routerBuilder
                            .operation("resta")
                            .handler(this::resta);

                   Router router = routerBuilder.createRouter();
                    HttpServer server = vertx.createHttpServer();
                    server
                            .requestHandler(router)
                            .listen(config().getInteger("port"));
    });
                 }

    private void resta(RoutingContext routingContext) {

        RequestParameters params =
                routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);
        RequestParameter body = params.body();
        double x = body.getJsonObject().getDouble("x");
        double y = body.getJsonObject().getDouble("y");
        double result = x - y;


        HttpServerResponse response = routingContext.response();
        response
                .setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(new JsonObject().put("result", result).toBuffer());

    }

    private void suma(RoutingContext routingContext) {

        RequestParameters params =
                routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);
        RequestParameter body = params.body();
        double x = body.getJsonObject().getDouble("x");
        double y = body.getJsonObject().getDouble("y");
        double result = x + y;


        HttpServerResponse response = routingContext.response();
        response
                .setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(new JsonObject().put("result", result).toBuffer());

    }


}

