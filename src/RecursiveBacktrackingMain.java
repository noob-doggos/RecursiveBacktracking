import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecursiveBacktrackingMain
{
    //
    // Response 1: travel
    //

    public static String travelHelper(int x, int y, String pathCur)
    {
        if (x == 0 && y == 0)
        {
            return pathCur + "\n";
        }

        if (x == 0 && y == 1)
        {
            return pathCur + "N\n";
        }

        if (x == 1 && y == 0)
        {
            return pathCur + "E\n";
        }

        String path = "";

        if (x > 0 && y > 0)
        {
            path += travelHelper(x - 1, y - 1, pathCur + "NE ");
        }

        if (x > 0)
        {
            path += travelHelper(x - 1, y, pathCur + "E ");
        }

        if (y > 0)
        {
            path += travelHelper(x, y - 1, pathCur + "N ");
        }

        return path;
    }

    public static void travel(int x, int y)
    {
        System.out.print(travelHelper(x, y, ""));
    }

    //
    // Response 2: countBinary
    //

    public static void countBinaryHelper(int i, int n)
    {
        if (n == 0)
        {
            System.out.println();
            return;
        }

        int maxNumSpaces = Integer.toBinaryString((int) Math.pow(2, n - 1)).length();
        String targetStr = String.format("%" + maxNumSpaces + "s", "").replaceAll(" ", "1");
        String binaryNum = Integer.parseInt(Integer.toBinaryString(i)) + "";
        String binaryNumFormatted = String.format("%" + maxNumSpaces + "s", binaryNum + "").replaceAll(" ", "0");

        System.out.println(binaryNumFormatted);

        if (binaryNum.equals(targetStr))
        {
            return;
        }

        countBinaryHelper(i + 1, n);
    }

    public static void countBinary(int n)
    {
        countBinaryHelper(0, n);
    }

    //
    // Response 3: maxSum
    //

    public static void maxSumHelper(List<Integer> L, int index, int limit, int sum, List<Integer> owo)
    {
        if (sum > limit)
        {
            return;
        }

        owo.add(sum);

        if (index >= L.size())
        {
            return;
        }

        int num = L.get(index);
        maxSumHelper(L, index + 1, limit, sum + num, owo);
        maxSumHelper(L, index + 1, limit, sum, owo);
    }

    public static int maxSum(List<Integer> L, int n)
    {
        if (L.size() == 0 || n <= 0)
        {
            return 0;
        }

        List<Integer> owo = new ArrayList<>();
        maxSumHelper(L, 0, n, 0, owo);
        Collections.sort(owo);
        return owo.get(owo.size() - 1);
    }

    //
    // Response 4: partitionable
    //

    public static boolean partitionableHelper(List<Integer> list, int index, int sum1, int sum2)
    {
        if (index == list.size())
        {
            return sum1 == sum2;
        }
        
        return partitionableHelper(list, index + 1, sum1 + list.get(index), sum2)
            || partitionableHelper(list, index + 1, sum1, sum2 + list.get(index));
    }

    public static boolean partitionable(List<Integer> list)
    {
        return partitionableHelper(list, 0, 0, 0);
    }

    public static void main(String[] args)
    {
        travel(2, 2);
        countBinary(5);
        List<Integer> L = new ArrayList<>();
        L.add(7);
        L.add(30);
        L.add(8);
        L.add(22);
        L.add(6);
        L.add(1);
        L.add(14);
        System.out.println(maxSum(L, 19));
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(6);
        System.out.println(partitionable(list));
    }

}
