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
        // check base cases to return on.
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

        // try branching our paths.
        // we can start going northeast, east or north.
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
        // base case: if n = 0, print only a blank line and return.
        if (n == 0)
        {
            System.out.println();
            return;
        }

        // hacky code using string manipulation to convert base-10 ints i and
        // 2^(n-1) to string with base-2 equivalent padded with zeroes
        int maxNumSpaces = Integer.toBinaryString((int) Math.pow(2, n - 1)).length();
        String targetStr = String.format("%" + maxNumSpaces + "s", "").replaceAll(" ", "1");
        String binaryNum = Integer.parseInt(Integer.toBinaryString(i)) + "";
        String binaryNumFormatted = String.format("%" + maxNumSpaces + "s", binaryNum + "").replaceAll(" ", "0");

        System.out.println(binaryNumFormatted);

        // check if our current binary value equals the maximum binary value for
        // n bits. if so, return
        if (binaryNum.equals(targetStr))
        {
            return;
        }

        // print the next binary value incrementally
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
        // toss out this instance if our sum has exceeded the limit, as there
        // are no negative numbers in the list
        if (sum > limit)
        {
            return;
        }

        // add the current sum to the list of legal sums.
        owo.add(sum);

        // if our index is out of the bounds of our list L, return to prevent
        // NPE.
        if (index >= L.size())
        {
            return;
        }

        // spawn two more instances of this method, one where we add the current
        // list value, one where we don't to cover all possible combinations of
        // list elements. worst-case time complexity O(n^2)
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
        // if we have summed up all sub-lists within our list, we check whether
        // our left array sum == right array sum
        if (index == list.size())
        {
            return sum1 == sum2;
        }

        // spawn two more instances of this method, one where we add the current
        // element to the left sum, other one where we leave it out of the left
        // sum and add to right sum instead.
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
