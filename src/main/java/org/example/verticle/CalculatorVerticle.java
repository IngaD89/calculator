package org.example.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.SqlClient;
import org.example.routerContext.RContext;
import org.testcontainers.containers.PostgreSQLContainer;


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


                    PgConnectOptions connectOptions = new PgConnectOptions()
                            .setPort(config().getInteger("port", 5432))
                            .setHost("localhost")
                            .setDatabase("historydb")
                            .setUser("postgres")
                            .setPassword("password");


                    PoolOptions poolOptions = new PoolOptions()
                            .setMaxSize(5);


                    SqlClient client = PgPool.client(vertx, connectOptions, poolOptions);
                    client
                            .query("INSERT INTO historydb(id, operation, x, y, result, dateTime) " +
                                    "VALUES(1, '+', 2.0, 3.0, 5.0, '2021-09-21T10:15:30')")
                            .execute()
                            .compose(rs -> {
                                client
                                        .query("select * from historydb")
                                        .execute()
                                        .onSuccess(rows -> {
                                            for (Row row : rows) {
                                                System.out.println("row = " + row.toJson());
                                            }
                                        })
                                        .onFailure(Throwable::printStackTrace);


                                return null;

                });


        Router router = routerBuilder.createRouter();
        HttpServer server = vertx.createHttpServer();
        server
                .requestHandler(router)
                .listen(config().getInteger("port"));
    });

}

}

