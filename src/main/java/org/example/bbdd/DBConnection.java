package org.example.bbdd;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgConnectOptions;

import io.vertx.sqlclient.*;

import java.time.ZoneOffset;
import java.util.stream.Collectors;


public class DBConnection {


    PgConnectOptions options = new PgConnectOptions()
            .setPort(5432)
            .setHost("localhost")
            .setDatabase("historydb")
            .setUser("postgres")
            .setPassword("example");

    public Future<JsonArray> postResta(String operation,
                                       double x,
                                       double y,
                                       double result) {


        Pool pool = Pool.pool(Vertx.currentContext().owner(), options, new PoolOptions().setMaxSize(4));

        return pool
                .query("INSERT INTO historydb(operation, x, y, result)" +
                        " VALUES('" + operation + "', '" + x + "', '" + y + "', '" + result + "')")
                .collecting(Collectors.mapping(row -> new JsonObject()
                                .put("operation", row.getString("operation"))
                                .put("x", row.getDouble("x"))
                                .put("y", row.getDouble("y"))
                                .put("result", row.getDouble("result"))
                        , Collectors.toList())
                )
                .execute()
                .map(SqlResult::value)
                .map(JsonArray::new);
    }


    public Future<JsonArray> postSuma(String operation,
                                      double x,
                                      double y,
                                      double result) {


        Pool pool = Pool.pool(Vertx.currentContext().owner(), options, new PoolOptions().setMaxSize(4));

        return pool
                .query("INSERT INTO historydb(operation, x, y, result)" +
                        " VALUES('" + operation + "', '" + x + "', '" + y + "', '" + result + "')")
                .collecting(Collectors.mapping(row -> new JsonObject()
                                .put("operation", row.getString("operation"))
                                .put("x", row.getDouble("x"))
                                .put("y", row.getDouble("y"))
                                .put("result", row.getDouble("result"))
                        , Collectors.toList())
                )
                .execute()
                .map(SqlResult::value)
                .map(JsonArray::new);
    }


    public Future<JsonArray> getHistory() {

        Pool pool = Pool.pool(Vertx.currentContext().owner(), options, new PoolOptions().setMaxSize(4));

        return pool
                .query("SELECT * FROM historydb")
                .collecting(Collectors.mapping(row -> new JsonObject()
                                .put("id", row.getLong("id"))
                                .put("operation", row.getString("operation"))
                                .put("x", row.getDouble("x"))
                                .put("y", row.getDouble("y"))
                                .put("result", row.getDouble("result"))
                                .put("dateTime", row.getLocalDateTime("datetime").toInstant(ZoneOffset.UTC))
                        , Collectors.toList())
                )
                .execute()
                .map(SqlResult::value)
                .map(JsonArray::new);


    }

    public Future<JsonArray> getById(Long id) {


        Pool pool = Pool.pool(Vertx.currentContext().owner(), options, new PoolOptions().setMaxSize(4));

        return pool
                .query("SELECT * FROM historydb Where id=" + id + "")
                .collecting(Collectors.mapping(row -> new JsonObject()
                                .put("id", row.getLong("id"))
                                .put("operation", row.getString("operation"))
                                .put("x", row.getDouble("x"))
                                .put("y", row.getDouble("y"))
                                .put("result", row.getDouble("result"))
                                .put("dateTime", row.getLocalDateTime("datetime").toInstant(ZoneOffset.UTC))
                        , Collectors.toList())
                )
                .execute()
                .map(SqlResult::value)
                .map(JsonArray::new)
                .compose(list-> {
                    if(list.isEmpty()){
                        return Future.failedFuture("no encontrado");
                    }else{
                        return Future.succeededFuture(list);
                    }
                })
                ;
    }


}




