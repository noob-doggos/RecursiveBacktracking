
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

    public static void main(String[] args)
    {
        travel(2, 2);
        countBinary(5);
    }

}
