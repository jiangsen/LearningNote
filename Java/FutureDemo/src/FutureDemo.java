import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID    DATE            PERSON            REASON
 *  1     2018年7月19日         YC           Create
 * ****************************************************************************
 * </pre>
 * 
 * https://alleniverson.gitbooks.io/java-basic-introduction/content/%E7%AC%AC5%
 * E7%AB%A0%20%E5%A4%9A%E7%BA%BF%E7%A8%8B/Callable%E5%92%8CFuture.html
 * 
 * @author YC
 * @version 1.0
 */
public class FutureDemo {

    static ExecutorService mExecutor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        try {
            futureWithRunnable();
            futureWithCallable();
            futureTask();
        } catch (Exception e) {
        }
    }

    private static void futureWithRunnable() throws InterruptedException, ExecutionException {
        Future<?> result = mExecutor.submit(new Runnable() {

            @Override
            public void run() {
                fib(20);
            }
        });
        System.out.println("future result from runnable : " + result.get());
    }

    private static void futureWithCallable() throws InterruptedException, ExecutionException {
        Future<Integer> result = mExecutor.submit(new Callable<Integer>() {

            @Override
            public Integer call() {
                return fib(20);
            }
        });
        System.out.println("future result from callable : " + result.get());
    }

    private static void futureTask() throws InterruptedException, ExecutionException {
        FutureTask<Integer> futurTask = new FutureTask<Integer>(new Callable<Integer>() {

            @Override
            public Integer call() {
                return fib(20);
            }
        });
        mExecutor.submit(futurTask);
        System.out.println("future result from futureTask : " + futurTask.get());
    }

    private static Integer fib(int num) {
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }
        return fib(num - 1) + fib(num - 2);
    }

}
