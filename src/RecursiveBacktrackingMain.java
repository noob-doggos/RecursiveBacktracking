
public class RecursiveBacktrackingMain
{
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
        System.out.println(travelHelper(x, y, ""));
    }

    public static void main(String[] args)
    {
        travel(2, 2);
    }

}
