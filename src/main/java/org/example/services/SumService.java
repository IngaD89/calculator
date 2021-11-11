package org.example.services;

import io.vertx.core.Future;

public class SumService {
    public Future<Integer> sum(int x, int y){
        return Future.succeededFuture(x+y);
    }
}
