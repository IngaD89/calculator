package org.example.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.openapi.RouterBuilderOptions;
import io.vertx.ext.web.validation.RequestParameter;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;
import org.example.models.Calculator;
import org.example.services.CalculatorService;


public class CalculatorVerticle extends AbstractVerticle {

    @Override
    public void start() {
        RouterBuilder.create(vertx, "src/main/resources/openapi-spec.yaml")
                .onFailure(Throwable::printStackTrace)
                .onSuccess(routerBuilder -> {
                    routerBuilder.getOpenAPI().getOpenAPI();
                    System.out.println("prueba " + routerBuilder.getOpenAPI().getOpenAPI());
                        RouterBuilderOptions builderOptions = new RouterBuilderOptions()
                            .setMountResponseContentTypeHandler(true);
                    routerBuilder.setOptions(builderOptions);

                    routerBuilder
                            .operation("getcalculators")
                            .handler(routingContext -> {
                                RequestParameters params =
                                        routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);
                                RequestParameter body = params.body();
                               // JsonObject jsonBody = routerBuilder.getOpenAPI().getOpenAPI();
                                HttpServerResponse response = routingContext.response();
                                response
                                        .setStatusCode(200)
                                        .putHeader("content-type", "application/json");
                            });

                    Router router = routerBuilder.createRouter();

                    router
                            .get("/calculators/getcalculators")
                            .handler(this::getcalculators);
                    HttpServer server = vertx.createHttpServer();
                    server
                            .requestHandler(router)
                            .listen(config().getInteger("port"));
                });
    }

    private void getcalculators(RoutingContext routingContext) {
        Calculator calculator = new Calculator();

        JsonObject jsonObject = new JsonObject();

        CalculatorService calculatorService = new CalculatorService();

        double resultado;

        calculator.setX(4.0);
        calculator.setY(5.0);

        resultado = calculatorService.sumar(calculator.getX(), calculator.getY());

        calculator.setResult(resultado);

        System.out.println(calculator);

        jsonObject.put("suma", calculator);
        routingContext
                .response()
                .putHeader("content-type", "application/json")
                .end(jsonObject.encodePrettily());
    }
}

