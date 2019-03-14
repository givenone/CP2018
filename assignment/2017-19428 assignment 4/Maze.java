import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Maze {

    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner in = new Scanner(new File((args[0])));
        PrintStream out = new PrintStream(new File(args[1]));

        String[][] map = new String[128][128];
        int [][] visited = new int[128][128];
        int wid = 0, he = 0;

        while (in.hasNextLine())
        {
            map[he] = in.nextLine().split("\\t+");
            wid = map[he].length;
            he++;
        }
        out.println(traversal(map,he-1,0,wid,he,visited));
    }

    public static String traversal(String[][] map, int x, int y, int width, int height, int[][] visited)
    {// return minimum path
        if( x < 0 || y < 0 || x >= height || y >= width || map[x][y].equals("0") || visited[x][y] == 1)
            return null;
        if( x == 0 && y == width-1)
            return Integer.toString(width);

        visited[x][y] = 1;
        String[] res = new String[4];
        res[0] = traversal(map, x+1 , y, width, height,visited);
        res[1] = traversal(map, x-1, y, width, height,visited);
        res[2] = traversal(map, x, y+1, width, height,visited);
        res[3] = traversal(map, x, y-1, width, height,visited);
        visited[x][y] = 0;
        String fres = getmin(res);
        if(fres == null)
            return null;
        else
            return Integer.toString(x*width + y + 1) + "-" + fres;
    }

    public static String getmin(String[] r)
    {
        int [] len = new int[4];
        for(int i=0; i<4 ; i++)
        {
            if(r[i] == null) len[i] = 10000000;
            else
            { len[i] = r[i].split("-").length; }
        }
        int min = len[0];
        int index = 0;
        for(int i=1; i<4 ; i++)
        {
            if( min > len[i])
            {
                min = len[i];
                index = i;
            }
        }
        return r[index];
    }
}
