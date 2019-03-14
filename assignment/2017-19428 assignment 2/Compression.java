import java.util.*;
import java.io.*;

public class Compression {

	public static void main(String[] args) throws FileNotFoundException {
		
		File dictionary = new File("dictionary.txt");
		Scanner a = new Scanner(new File("input.txt"));
		File output = new File("output.txt");
		
		
		String[] input = a.nextLine().split(",");
		
		if(input[0].equals("H"))
		{
			if(input[1].equals("C"))
			{
				hcompression(dictionary, output, input[2]);
			}
			
			else
			{
				hdecompression(dictionary, output, input[2]);
			}
		}
		
		else if(input[0].equals("T"))
		{
			if(input[1].equals("C"))
			{
				tcompression(dictionary, output, input[2]);
			}
			
			else
			{
				tdecompression(dictionary, output, input[2]);
			}
			
		}
		
		a.close();

	}
	
	public static void hcompression(File dic, File out, String in) throws FileNotFoundException
	{
		PrintStream wr = new PrintStream(out);

		for(int i=0; i<in.length() ; i++)
		{
			Scanner diction = new Scanner(dic);
			while(diction.hasNextLine())
			{
				String[] temp = diction.nextLine().split(",");

				if(in.substring(i,i+1).equals(temp[0]))
				{// in the dictionary and it is the case
					wr.print(temp[1]);
					break;
				}
				
			}
			diction.close();
			
		}
		wr.close();
		
	}

	public static void hdecompression(File dic, File out, String in) throws FileNotFoundException
	{
		PrintStream wr = new PrintStream(out);

		for(int i=0; i<in.length() ; )
		{
			Scanner diction = new Scanner(dic);
			boolean flag = false; // for nothing same
						
			while(diction.hasNextLine())
			{
				String[] temp = diction.nextLine().split(",");
				try{

				if(in.substring(i, i+ temp[1].length()).equals(temp[1]))
				{
					wr.print(temp[0]);
					i = i + temp[1].length();

					flag = true;
					break;
					
				}

				}
				catch(Exception e)
				{
					continue;	
				}

			}

			diction.close();
			
			if(!flag)
			{
				System.out.println("ERROR.Nothing Same");
				return;
			}
			
		}
		wr.close();
		
	}
	
	public static void tcompression(File dic, File out, String in) throws FileNotFoundException
	{
		PrintStream wr = new PrintStream(out);

		for(int i=0; i<in.length() ; )
		{
			Scanner diction = new Scanner(dic);
			while(diction.hasNextLine())
			{
				String[] temp = diction.nextLine().split(",");
				try{

				if(in.substring(i,i+ temp[0].length()).equals(temp[0]))
				{// in the dictionary and it is the case
					wr.print(temp[1]);
					i += temp[0].length();
					break;
				}
					

				}
				catch(Exception e)
				{
					continue;	
				}

				
			}
			diction.close();
			
		}
		wr.close();
		
		
	}
	public static void tdecompression(File dic, File out, String in) throws FileNotFoundException
	{
		PrintStream wr = new PrintStream(out);

		for(int i=0; i<in.length() ; )
		{
			Scanner diction = new Scanner(dic);
			boolean flag = false; // for nothing same
						
			while(diction.hasNextLine())
			{
				String[] temp = diction.nextLine().split(",");
				

				if(in.substring(i, i+ temp[1].length()).equals(temp[1]))
				{
					wr.print(temp[0]);
					
					i = i + temp[1].length();

					flag = true;

					break;
					
				}
			}

			diction.close();
			
			if(!flag)
			{
				System.out.println("ERROR.Nothing Same");
				return;
			}
			
		}
		wr.close();
		
	}
}
