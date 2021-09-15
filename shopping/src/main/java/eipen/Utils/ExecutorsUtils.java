package eipen.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsUtils {
    protected static ExecutorService executorService;

    public static void setExecutorService(ExecutorService executorService) {
        ExecutorsUtils.executorService = executorService;
    }

    public static void shutdown(){
        executorService.shutdown();
    }
    public static void submit(Runnable runnable){
        executorService.submit(runnable);
    }

}
