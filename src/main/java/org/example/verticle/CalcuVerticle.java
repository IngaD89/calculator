package org.example.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;


public class CalcuVerticle extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);
        HttpServer server = vertx.createHttpServer();

        Route route = router
                .get("/add")
                .handler(routingContext -> {
                    HttpServerResponse response = routingContext.response();
                    response
                            .setChunked(true)
                            .write("here goes calculator");
                            response.end();
                });
        server
                .requestHandler(router)
                .listen(config().getInteger("port"));
    }
}
