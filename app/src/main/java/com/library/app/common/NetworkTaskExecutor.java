package com.library.app.common;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NetworkTaskExecutor {
    private static final Executor networkExecutor = Executors.newSingleThreadExecutor();
    public static void execute(Runnable task) {
            networkExecutor.execute(task);
    }
}

