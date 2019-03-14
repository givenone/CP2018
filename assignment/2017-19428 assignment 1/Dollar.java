import java.util.*;


public class Dollar {
	
	
	public static void main(String args[])
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Type the maximum length: ");
		
		int i = input.nextInt();
		input.nextLine();
		
		System.out.println("(a)");
		drawa(i);
		
		System.out.println("(b)");
		
		drawb(i);
		
		input.close();
	
	}
	
	
	static void drawa(int a)
	{
		int value = 1;
		int flag = 0;
		
		if( a%2 == 0)
			value = 2;
		
		for( ; value < a ;  )
		{
			printd(a,value);

			
			if(value + 4 <= a)
				value += 4;
			else if(value + 2 == a)
			{
				value += 2;
				flag = 1;
			}
		}
		
		printd(a,a);
		
		do
		{
			if(flag == 1)
			{
				value -= 2;
				flag = 0;
			}
			
			else if(flag == 0 && value-4 >= 1)
			{
				value -= 4;
			}
			
			printd(a,value);
			

			
		}while(value-4 >= (double)a/2);
			
		do
		{
			if(value + 2 == a)
			{
				value += 2;
				flag = 1;
			}
			
			else if( value + 4 <= a)
				value += 4;
			
			printd(a,value);
		}
		while(value != a);
		
		while(value != 1 && value != 2)
		{
			if(flag == 1) {

				value -= 2;
				flag = 0;
			}
			else if( value -4 >= 1)
			{
				value -= 4;
			}
			printd(a,value);
		}
		
	}

	static void drawb(int a)
	{
		int value = 1;
		int flag = 0;
		
		if( a%2 == 0)
			value = 2;
		
		for( ; value < a ;  )
		{
			printx(a,value);
			
			if(value + 4 <= a)
				value += 4;
			else if(value + 2 == a)
			{
				value += 2;
				flag = 1;
			}
		}
		
		printx(a,a);
		
		do
		{
			if(flag == 1)
			{
				value -= 2;
				flag = 0;
			}
			
			else if(flag == 0 && value-4 >= 1)
			{
				value -= 4;
			}
			
			if(value <= (double)a/2)
				printspecial(a, value);
			else
				printx(a,value);
			

			
		}while(value-4 >= (double)a/2);
			
		do
		{
			if(value + 2 == a)
			{
				value += 2;
				flag = 1;
			}
			
			else if( value + 4 <= a)
				value += 4;
			
			printx(a,value);
		}	while(value != a);
		
		while(value != 1 && value != 2)
		{
			if(flag == 1) {

				value -= 2;
				flag = 0;
			}
			else if( value -4 >= 1)
			{
				value -= 4;
			}
			printx(a,value);
		}
			
	}
	
	static void printspecial(int length, int x)
	{
		for(int i=1; i<=length ;i++)
		{
			if(i == (length - x)/2  + 1 || i == (length + x)/2 )
				System.out.print("$");
			
			else if(i == (length - x)/2   || i == (length + x)/2 + 1)
				System.out.print("@");
				
			else 
				System.out.print(" ");	
		}
		System.out.println();
	}
	
	static void printx(int length, int x)
	{
		for(int i=1; i<=length ; i++)
		{
			if(i == (length - x)/2  + 1 || i == (length + x)/2 )
				System.out.print("$");
			else
				System.out.print(" ");
		}
		System.out.println();
	}
	
	static void printd(int length, int x)
	{
		for(int i=0; i< (length-x)/2 ; i++)
		{
			System.out.print(" ");
		}
		for(int i=0; i<x ; i++)
		{
			System.out.print("$");
		}
		for(int i=0; i< (length-x)/2 ; i++)
		{
			System.out.print(" ");
		}
		System.out.println();
	}
	
	

}
