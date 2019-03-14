import java.util.*;

public class Tennis {

	public static void main(String[] args) {
	
		Scanner input = new Scanner(System.in);

		System.out.print("Type the match (A: Australian Open/U: US Open): ");
		char type = input.next().charAt(0);
		input.nextLine();
		
		System.out.print("Type the gender (F: Female/M: Male): ");
		char sex = input.next().charAt(0);
		input.nextLine();
		
		match(type,sex);
		
		
		
		input.close();
	}

	public static void match(char type, char sex)
	{
		int set1 = 0;
		int set2 = 0;
		int round = 1;
		int g1 = 0;
		int g2 = 0;
		int score1 = 0;
		int score2 = 0;
		String res = "Current:";
		boolean flag = true;
		
		if(type == 'A')
			System.out.print("Australian Open//");
		else
			System.out.print("US Open//");
		if(sex == 'M')
			System.out.println("Male Chosen.");
		else
			System.out.println("Female Chosen.");
		
		Scanner x = new Scanner(System.in);
		
		while(flag)
		{
			System.out.println(res + printset(set1,set2));
			
			if((set1== 6 && set2 <= 4) ||( set1 == 7 && set2 == 5) || (type == 'A' && ((round == 5 && sex == 'M')|| (round == 3 && sex == 'F'))&&set2>=6&& (set1-set2 == 2) )) // needs to increase 
			{
				g1++;
				round++;
				res = res+printset(set1,set2);
				score1 = 0;
				score2 = 0;
				set1 = 0;
				set2 = 0; // return
				// 1 wins
			}
			else if((set2 == 6 && set1 <=4) ||  (set2 == 7 && set1 == 5) || (type == 'A' && ((round == 5 && sex == 'M')|| (round == 3 && sex == 'F'))&& set1>=6&& (set2-set1 == 2) ))
			{
				g2++;
				round++;
				res = res+printset(set1,set2);
				score1 = score2 = set1 = set2 = 0;
				
				// 2 wins
			}
			else if(set1 == 6 && set2 == 6 && !(type == 'A' && ((sex == 'F' && round == 3) || (sex == 'M'&& round == 5))))
			{ // tie break; // only with us open?


				int s1 = 0;
				int s2 = 0;
				while(true)
				{

					System.out.print("Type the winner (L: Left/R: Right): ");
					char in = x.next().charAt(0);
					x.nextLine();
					if(in == 'L')
					{
						if(s1 >= 6 && s2 >= 6)
						{
							if(s1 - s2 == 1)
							{
								set1++;
								s1++;
								res = res+printset(set1,set2)+"("+Integer.toString(s1)+"-"+Integer.toString(s2)+")";
								g1++;
								round++;
								score1=score2=set1=set2=0;
								System.out.println(res);
								break;
							}
							
							else
							{
								s1++;
							}
						}
						else
						{
							if(s1 == 6 )
							{
								set1++;
								s1++;
								res = res+printset(set1,set2)+"("+Integer.toString(s1)+"-"+Integer.toString(s2)+")";
								g1++;
								round++;
								score1=score2=set1=set2=0;
								System.out.println(res);
								break;
							}
							s1++;
						
						}	
					}
					
					else
					{
						if(s2 >=6 && s1 >= 6)
						{
							if(s2 - s1 == 1)
							{
								set2++;
								s2++;
								res = res+printset(set1,set2)+"("+Integer.toString(s1)+"-"+Integer.toString(s2)+")";
								g2++;
								round++;
								score1=score2=set1=set2=0;
								System.out.println(res);
								break;
								
							}
							else
							{
								s2++;
							}
							
						}
						else
						{

							if(s2 == 6)
							{
								set2++;
								s2++;
								res = res+printset(set1,set2)+"("+Integer.toString(s1)+"-"+Integer.toString(s2)+")";
								g2++;
								round++;
								score1=score2=set1=set2=0;
								System.out.println(res);
								break;
								
							}
							s2++;
							
						}
					}
					
					
					System.out.println(res + printset(set1,set2) + "("+Integer.toString(s1)+"-"+Integer.toString(s2)+")");
				}
				
			} // to here : set scores 
			
			// whether or not to end the game!
			
			if(sex == 'M' && (g1 == 3 || g2 == 3) )
			{
				System.out.println("Game finished!");
				return;
			}
			else if(sex == 'F' && (g1 == 2 || g2 == 2))
			{
				System.out.println("Game finished!");
				return; // end the game.
			}
			
			while(true) // game scores starts here!
			{
				System.out.print("Type the winner (L: Left/R: Right): ");
				char in = x.next().charAt(0);
				x.nextLine();
				if(in == 'L')
				{// 15 : 1 , 30 : 2, 40 : 3, 40A : 4
					if(score1 < 3)
					{
						score1 += 1;
					}
					else if((score1 == 3 && score2 < 3) || score1 == 4)
					{
						set1 += 1;
						score1 = 0;
						score2 = 0;
						break; // win
					}
					else if(score1 == 3 && score2 == 3)
					{
						score1 += 1;
					}
					else if(score1 == 3 && score2 == 4)
					{
						score2 -= 1;
					}
					
				}
				else
				{
					if(score2 < 3)
					{
						score2 += 1;
					}
					else if((score2 == 3 && score1 < 3) || score2 == 4)
					{
						set2+= 1;
						score2 = 0;
						score1 = 0;
						break;// win
					}
					else if(score2 == 3 && score1 == 3)
					{
						score2 += 1;
					}
					else if(score2 == 3 && score1 == 4)
					{
						score1 -= 1;
					}
					
				}
					
				System.out.println(res + printset(set1, set2) + printscore(score1, score2));
			}
		}
		x.close();
	}
	
	public static String printset(int set1, int set2)
	{
		return(" "+Integer.toString(set1)+"-"+Integer.toString(set2));
	}
	
	public static String printscore(int score1, int score2)
	{ 
		String a= "";
		String b= "";
		if(score1 == 1)
			score1 = 15;
		else if(score1 == 2)
			score1 = 30;
		else if(score1 == 3)
			score1 = 40;
		else if(score1 == 4)
		{
			score1 = 40;
			a = "A";
		}

		if(score2 == 1)
			score2 = 15;
		else if(score2 == 2)
			score2 = 30;
		else if(score2 == 3)
			score2 = 40;
		else if(score2 == 4)
		{
			score2 = 40;
			b = "A";
		}
		return "("+Integer.toString(score1)+a+"-"+ Integer.toString(score2)+b+")";
	}
}
