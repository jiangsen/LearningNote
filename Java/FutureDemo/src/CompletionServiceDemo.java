import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID    DATE            PERSON            REASON
 *  1     2018年7月21日         YC           Create
 * ****************************************************************************
 * </pre>
 * 
 * @author YC
 * @version 1.0
 */
public class CompletionServiceDemo {

    public static void main(String args[]) {
        ExecutorService thread = Executors.newSingleThreadExecutor();
        Future future = thread.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "hello";
            }
        });
        System.out.println("等待結果……");

        // 在指定时timeout内等待，未等到抛出TimeoutException
        // System.out.println("拿到结果：" + future.get(long timeout,TimeUnit unit));

        try {
            System.out.println("拿到结果：" + future.get()); // 获取线程执行后的结果
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ExecutorService thread2 = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(
                thread2);

        for (int i = 0; i < 10; i++) {
            final int seq = i;
            completionService.submit(new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    Thread.sleep(new Random().nextInt(5000));
                    return seq;
                }
            });
        }
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("the thread result is : " + completionService.take().get());
            } catch (InterruptedException | ExecutionException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
    }

}
