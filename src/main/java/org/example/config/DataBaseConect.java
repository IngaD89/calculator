package org.example.config;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.*;

public class DataBaseConect extends AbstractVerticle {

    @Override
    public void start() throws Exception {


           PgConnectOptions connectOptions = new PgConnectOptions()
                .setPort(config().getInteger("port", 5432))
                .setHost("localhost")
                .setDatabase("historydb")
                .setUser("postgres")
                .setPassword("password");

// Pool options
        PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(5);

// Create the pooled client
             SqlClient client = PgPool.client(vertx, connectOptions, poolOptions);
          client
                .query("INSERT INTO historydb(id, operation, x, y, result, dateTime) " +
                        "VALUES(4, '+', 2.0, 3.0, 5.0, '2021-09-21T10:15:30')")
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


                    return   Future.succeededFuture();
                });
    }
}