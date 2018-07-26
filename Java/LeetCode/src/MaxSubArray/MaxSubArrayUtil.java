package MaxSubArray;

/**
 * 
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID    DATE            PERSON            REASON
 *  1     2018年7月26日         YC           Create
 * ****************************************************************************
 * </pre>
 * 
 * @author YC
 * @version 1.0
 */
public class MaxSubArrayUtil {
    public static int Function(int[] number) {
        int[] sum = new int[number.length];
        int max = number[0];
        sum[0] = number[0];
        for(int i = 1;i < number.length;i++) {
            sum[i] = Math.max(number[i], sum[i-1] + number[i]);
            max = Math.max(sum[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] number = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println(Function(number));
    }

}
