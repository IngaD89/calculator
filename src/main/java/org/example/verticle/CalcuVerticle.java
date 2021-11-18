package org.example.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.example.models.Calculator;
import org.example.services.CalculatorService;


public class CalcuVerticle extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);
        HttpServer server = vertx.createHttpServer();

         router
                .get("/add")
                .handler(this::handleAddOperation);
        server
                .requestHandler(router)
                .listen(config().getInteger("port"));
    }

    private void handleAddOperation(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();

        Calculator calculator = new Calculator();

        CalculatorService calculatorService = new CalculatorService();

        double resultado;

        calculator.setX(4.0);
        calculator.setY(5.0);

        resultado  = calculatorService.sumar(calculator.getX(), calculator.getY());

        calculator.setResult(resultado);

        System.out.println(calculator);
    }
}
