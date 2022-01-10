package org.example.routerContext;


import io.vertx.core.http.HttpHeaders;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.validation.RequestParameter;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;
import org.example.bbdd.DBConnection;



public class RContext {

    DBConnection dbConnection = new DBConnection();

    public void resta(RoutingContext routingContext) {

        RequestParameters params =
                routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);
        RequestParameter body = params.body();
        String operation = body.getJsonObject().getString("operation", "-");
        double x = body.getJsonObject().getDouble("x");
        double y = body.getJsonObject().getDouble("y");
        double result = x - y;


        dbConnection.postResta(operation, x, y, result)
                .onFailure(Throwable::printStackTrace)
                .onSuccess(fut -> routingContext.response()
                        .setStatusCode(200)
                        .end(new JsonObject()
                                .put("x", x)
                                .put("y", y)
                                .put("result", result)
                                .toBuffer()
                        )
                );

    }

    public void suma(RoutingContext routingContext) {

        RequestParameters params =
                routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);
        RequestParameter body = params.body();
        String operation = body.getJsonObject().getString("operation", "+");
        double x = body.getJsonObject().getDouble("x");
        double y = body.getJsonObject().getDouble("y");
        double result = x + y;


        dbConnection.postSuma(operation, x, y, result)
                .onFailure(Throwable::printStackTrace)
                .onSuccess(fut -> routingContext.response()
                        .setStatusCode(200)
                        .end(new JsonObject()
                                .put("result", result)
                                .toBuffer()
                        )
                );


    }

    public void history(RoutingContext routingContext) {

        dbConnection.getHistory()
                .onFailure(Throwable::printStackTrace)
                .onSuccess(future -> routingContext.response()
                        .setStatusCode(200)
                        .end(future.toBuffer())
                );


    }

    public void getHistoryById(RoutingContext routingContext) {

        RequestParameters params = routingContext.get("parsedParameters");

        Long id = params.pathParameter("id").getLong();



        dbConnection.getById(id)

                .onFailure(t -> routingContext.response()
                        .setStatusCode(404)
                        .end(t.getMessage()))

                .onSuccess(fut -> routingContext.response()
                        .setStatusCode(200)
                        .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                        .end(fut.toBuffer()));

    }


}









