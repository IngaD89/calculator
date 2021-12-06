package org.example.routerContext;


import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.validation.RequestParameter;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;


public class RContext {

    public void resta(RoutingContext routingContext) {

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

    public void suma(RoutingContext routingContext) {

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

    public void history(RoutingContext routingContext){
        RequestParameters params =
                routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);
        RequestParameter body = params.body();
        double x = body.getJsonObject().getDouble("x");
        double y = body.getJsonObject().getDouble("y");
        double result = body.getJsonObject().getDouble("result");
        String dateTime = body.getJsonObject().getString("dateTime");


        HttpServerResponse response = routingContext.response();
        response
                .setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(new JsonObject()
                        .put("x", x)
                        .put("y", y)
                        .put("result", result)
                        .put("dateTime", dateTime)
                        .toBuffer());
    }
}
