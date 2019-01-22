/**
 * Tester class that will demo a F,W,G game and computer will predict the users move based on record patterns stored in a
 * hashmap in the Computer class
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class main 
{
	public static void main(String args[])
	{
		Computer comp = new Computer();
		
		String pattern ="";
		String letters= "";
		String moves = "";
		String emoves = "";
		
		File f = new File("pattern.dat");
	
		if(f.exists())
		{
			System.out.println("Would you like to play in Veteran or Beginner mode?");
			System.out.println("1. Veteran");
			System.out.println("2. Beginner");
			
			int input = CheckInput.checkIntRange(1,2);
			if(input == 1)
			{
				try
				{
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
					comp = (Computer) in.readObject();
					
					in.close();
				}catch(IOException e){
					System.out.println("Error processing");
				}catch(ClassNotFoundException e)
				{
					System.out.println("Could not find file.");
				}
			}
			else
			{
				comp = new Computer();
			}
		}
		
		boolean loop = true;
		int winU = 0;
		int winC = 0;
		double percent = 0;
		int total = 0;
		while(loop)
		{	
			int choice = menu();
				
			if(choice == 1)
			{
				letters = "F";
				moves = "Fire";
			}
				
			else if(choice == 2)
			{
				letters = "W";
				moves = "Water";
			}
				
			else if(choice == 3)
			{
				letters = "G";
				moves = "Grass";
			}
			else if(choice == 4)
			{
				System.out.println ("Would you like to save the data?");
				System.out.println ("1. Yes");
				System.out.println("2. No");
				int input = CheckInput.checkIntRange(1,2);
				if(input == 1)
				{
					System.out.println("Your game is saved!");
					try
					{
						ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
						out.writeObject(comp);
						out.close();
					}catch(IOException e)
					{
						System.out.println("Error processing file");
					}
					loop = false;
				}
				
					
				else
				{
					System.out.println("Thanks for playing!");
					loop = false;
				}
				return;
			}	
			String computerCheck = comp.makePrediction(pattern);
			
			if(computerCheck == "W")
			{
				emoves = "Water";
			}
			
			else if(computerCheck == "G")
			{
				emoves = "Grass";
			}
			
			else
			{
				emoves = "Fire";
			}
				
			if(pattern.length() >= 3)
			{
				pattern = pattern + letters;
				pattern = pattern.substring(pattern.length()-3, pattern.length());
				comp.storePattern(pattern);
					
			}
				
			else
			{
				pattern = pattern + letters;
			}
			
				
			if(choice == 1 && computerCheck == "G")
			{
				winU++;
				total++;
				System.out.println("You used Fire!");
				System.out.println("The opponent used Grass!");
				System.out.println("You win!");
				System.out.println("Your Wins: " + winU);
				System.out.println("Opponent wins: " +winC);
				System.out.printf("Opponent Win Percentage: %.2f", percent);
				System.out.println("");
			}
				
			else if(choice == 2 && computerCheck == "F")
			{
				winU++;
				total++;
				System.out.println("You used Water!");
				System.out.println("The opponent used Fire!");
				System.out.println("You win!");
				System.out.println("Your Wins: " + winU);
				System.out.println("Opponent Wins: " +winC);
				System.out.printf("Opponent Win Percentage: %.2f", percent);
				System.out.println("");
			}
				
			else if( choice == 3 && computerCheck == "W")
			{
				winU++;
				total++;
				System.out.println("You used Grass!");
				System.out.println("The opponent used Water!");
				System.out.println("You win!");
				System.out.println("Your Wins: " + winU);
				System.out.println("Opponent Wins: " +winC);
				System.out.printf("Opponent Win Percentage: %.2f", percent);
				System.out.println("");
			}
				
			else if(choice == 1 && computerCheck == "F" || choice == 2 && computerCheck == "W" || choice == 3 && computerCheck == "G")
			{
				System.out.println("You used " + moves);
				System.out.println("Your opponent used " + emoves);
				System.out.println("It's a draw");
				System.out.println("Your Wins: " + winU);
				System.out.println("Opponent Wins: " +winC);
				System.out.printf("Opponent Win Percentage: %.2f", percent);
				System.out.println("");
			}
				
			else //(choice == 1 && computerCheck == "W" || choice == 2 && computerCheck == "G" || choice == 3 && computerCheck == "F")
			{
				winC++;
				total++;
				percent = ((double)winC) / total;
				percent = percent*100;
				System.out.println("You used " + moves);
				System.out.println("The opponent used " + emoves);
				System.out.println("You lose!");
				System.out.println("Your Wins: " + winU);
				System.out.println("Opponent Wins: " +winC);
				System.out.printf("Opponent Win Percentage: %.2f", percent);
				System.out.println("");
			}
				
		}
			
	}
			
	public static int menu()
	{
		System.out.println("What would you like to do?");
		System.out.println("1. Fire\n2. Water\n3. Grass\n4. Exit");
		int menuChoice = CheckInput.checkIntRange(1,4);
		return menuChoice;
	}

}