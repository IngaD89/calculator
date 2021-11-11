package org.example.apis;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public class SumApi {
    public Future<JsonObject> apiSum(int x, int y){
        return sum(x,y)
                .map(suma -> new JsonObject().put("result", suma));
    }

    private Future<JsonObject> sum(int x, int y) {
        return null;
    }
}
