package cn.itcast.travel.util;

import java.util.List;
import java.util.concurrent.*;

public abstract class ExecutorsUtil {
  private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

  public static void shutdown()
  {
    EXECUTOR_SERVICE.shutdown();
  }

  public static Future submit(Callable task)
  {
    return EXECUTOR_SERVICE.submit(task);
  }

  public static void execute(Runnable task)
  {
    EXECUTOR_SERVICE.execute(task);
  }

  public static List<Future<Object>> invokeAll(List<? extends Callable<Object>> callableList, long timeout, TimeUnit unit) throws InterruptedException {
    return EXECUTOR_SERVICE.invokeAll(callableList, timeout, unit);
  }
}