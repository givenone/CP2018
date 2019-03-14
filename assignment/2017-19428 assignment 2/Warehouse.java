import java.util.*;
import java.io.*;

public class Warehouse {

	public static void main(String[] args) throws FileNotFoundException {
// stock.txt && tx.txt are already given.

		
		Scanner in = new Scanner(new File("input.txt"));
		File stock = new File("stock.txt");
		PrintStream fortx = new PrintStream(new File("tx.txt"));
		
		int txid =1;
		
		while(in.hasNextLine())
		{
			String line = in.nextLine();
			String[] words = line.split(","); 
/*			
 * 			I,<part type>,<part name>,<part price>,<part quantity>
			 S,<part type>,<part name>,<tx quantity>
			 E,<part type>,<part name>,<tx quantity> */
			
			if(words[0].equals("I"))
			{
				imports(words, stock, fortx, txid);
				txid++;
			}
			else if(words[0].equals("S"))
			{
				if(!sells(words, stock, fortx, txid))
				{
					break;
				}
				txid++;
			}
			else if(words[0].equals("E"))
			{
				if(!exchanges(words, stock, fortx, txid))
				{
					break;
				}
				txid++;
			}
			
		}
		
		
		
		in.close();
		
		
	}

	public static String remake(String[] splitted)
	{
		String a = "";
		for(int i =0 ; i<splitted.length -1 ; i++)
		{
			a += (splitted[i] + ",");
		}
		a += splitted[splitted.length-1]; // fencepost
		return a;
	}
	
	public static void imports(String[] words, File stock, PrintStream fortx, int txid) throws FileNotFoundException
	{
		File temp = new File("tempfile.txt");
		PrintStream fortemp = new PrintStream(temp);

		
		Scanner st = new Scanner(stock); // read stock.txt
		
		boolean flag = false;
		int snum = 0;
		
		while(st.hasNextLine())
		{
			snum ++;
			
			String[] now = (st.nextLine()).split(",");
			// updated price & quantity
			
			if(now[1].equals(words[1]) && now[2].equals(words[2])) // type and name is same
			{
				flag = true;

				if(now[3].compareTo(words[3]) > 0)
				{ // new one is cheaper

					System.out.println("The part became cheap.");
					now[3] = words[3];
					now[4] = Integer.toString(Integer.parseInt(now[4]) + Integer.parseInt(words[4]));
					fortemp.println(remake(now));
					fortx.printf("%d,%s,%d,%s,%s,%d\n", txid,words[0],snum,now[3],words[4],Integer.parseInt(now[3]) * Integer.parseInt(words[4]));
					// tx!
					
				}
				else if(now[3].compareTo(words[3]) < 0)
				{ // new one is more expensive
					
					System.out.println("The part became expensive.");
					now[3] = words[3];
					now[4] = Integer.toString(Integer.parseInt(now[4]) + Integer.parseInt(words[4]));
					fortemp.println(remake(now));
					fortx.printf("%d,%s,%d,%s,%s,%d\n", txid,words[0],snum,now[3],words[4],Integer.parseInt(now[3]) * Integer.parseInt(words[4]));
					// tx!
				}
				else
				{ // same price
					now[4] = Integer.toString(Integer.parseInt(now[4]) + Integer.parseInt(words[4]));
					fortemp.println(remake(now));
					fortx.printf("%d,%s,%d,%s,%s,%d\n", txid,words[0],snum,now[3],words[4],Integer.parseInt(now[3]) * Integer.parseInt(words[4]));
					// tx!
				}
			}
			else // when nothing smae -> just print for temp(stock)
			{
				fortemp.println(remake(now));
			}
		}
		st.close();
		if(!flag)
		{// nothing same.
		
			fortemp.printf("%d,%s,%s,%s,%s\n",++snum,words[1],words[2],words[3],words[4]);
			
			//stock!
			fortx.printf("%d,%s,%d,%s,%s,%d\n", txid,words[0],snum,words[3],words[4],Integer.parseInt(words[3]) * Integer.parseInt(words[4]));
			// tx!
		}

		fortemp.close();

		
		if(!stock.delete())
		{
			System.out.println("can not delete");
		}
		
		if(!temp.renameTo(stock))
		{
			System.out.println("can not rename file");
		}
		
		
	}
	
	public static boolean sells(String[] words, File stock, PrintStream fortx, int txid) throws FileNotFoundException
	{
		File temp = new File("tempfile.txt");
		PrintStream fortemp = new PrintStream(temp);
		
		Scanner st = new Scanner(stock); // read stock.txt
		
		boolean flag = false;
		int snum = 0;
		
		while(st.hasNextLine())
		{
			snum ++;
			
			String[] now = (st.nextLine()).split(",");
			// tosell price & quantity
			
			if(now[1].equals(words[1]) && now[2].equals(words[2])) // type and name is same
			{
				flag = true;

				if((Integer.parseInt(now[4]) - Integer.parseInt(words[3])) >= 0)
				{ // can sell

					now[4] = Integer.toString(Integer.parseInt(now[4]) - Integer.parseInt(words[3]));
					
					fortemp.println(remake(now));
					fortx.printf("%d,%s,%d,%s,%s,%d\n", txid,words[0],snum,now[3],words[3],Integer.parseInt(words[3]) * Integer.parseInt(now[3]));
					// tx!

				}
				
				else
				{ // cannot sell!

					return false;
				}
			}
			
			else // when nothing same -> just print for temp(stock)
			{
				fortemp.println(remake(now));
			}
		}
		
		st.close();
		
		if(!flag)
		{// nothing same. -> error

			return false;
		}

		fortemp.close();

		if(!stock.delete())
		{
			System.out.println("can not delete");
		}
		
		if(!temp.renameTo(stock))
		{
			System.out.println("can not rename file");
		}
		
		return true;

	}
	
	public static boolean exchanges(String[] words, File stock, PrintStream fortx, int txid) throws FileNotFoundException
	{

	
		Scanner st = new Scanner(stock); // read stock.txt
		
		boolean flag = false;
		int snum = 0;
		
		while(st.hasNextLine())
		{
			snum ++;
			
			String[] now = (st.nextLine()).split(",");
			// toexchange price & quantity
			
			if(now[1].equals(words[1]) && now[2].equals(words[2])) // type and name is same
			{
				flag = true;

				if(now[4].compareTo(words[3]) >= 0)
				{ // can exchange fully

					fortx.printf("%d,%s,%d,%s,%s,%d\n", txid,words[0],snum,now[3],words[3],Integer.parseInt(now[3]) * Integer.parseInt(words[3]));
					// only tx!

				}
				
				else
				{ // partially exchange
					System.out.println("The part partially exchanged.");

					fortx.printf("%d,%s,%d,%s,%s,%d\n", txid,words[0],snum,now[3],now[4],Integer.parseInt(now[3]) * Integer.parseInt(now[4]));
					// only tx!
								
				}
			}
			
			
		}
		
		st.close();

		if(!flag)
		{// nothing same. -> error
		
			return false;
		}

		
		
		return true;

		
	}
}
