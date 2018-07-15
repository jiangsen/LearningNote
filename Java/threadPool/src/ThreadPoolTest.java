

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID    DATE            PERSON            REASON
 *  1     2018年7月15日         YC           Create
 * ****************************************************************************
 * </pre>
 * 
 * @author YC
 * @version 1.0
 */
public class ThreadPoolTest {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the base dictory:");
        String dictory = in.nextLine();
        System.out.println("Enter the keyword:");
        String keyword = in.nextLine();
        ExecutorService pool = Executors.newCachedThreadPool();
        File file = new File(dictory);
        MatchCounter counter = new MatchCounter(file, keyword, pool);
        Future<Integer> result = pool.submit(counter);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
        System.out.print("max Pool size is : " + largestPoolSize);

    }

}

class MatchCounter implements Callable<Integer> {

    Integer count;
    File dictory;
    String keyword;
    private ExecutorService pool;

    public MatchCounter(File dictory, String keyword, ExecutorService pool) {
        this.dictory = dictory;
        this.keyword = keyword;
        this.pool = pool;
    }

    public Integer call() {
        count = 0;
        List<Future<Integer>> results = new ArrayList<>();
        File[] files = dictory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                MatchCounter cunter = new MatchCounter(file, keyword, pool);
                Future<Integer> result = pool.submit(cunter);
                results.add(result);
            } else {
                if (search(file))
                    count++;
            }
        }
        try {
            for (Future<Integer> result : results) {

                count += result.get();

            }
        } catch (InterruptedException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return count;
    }

    boolean search(File file) {
        boolean found = false;
        try {
            Scanner in = new Scanner(file);
            while (!found && in.hasNextLine()) {
                String line = in.nextLine();
                found = line.contains(keyword);
            }
            in.close();
        } catch (FileNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

        return found;
    }
}
