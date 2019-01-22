/**
 * Class function server that will create a server host and will read in input from user in the client class
 * Will use input to make prediction and to store the pattern
 */
import java.net.*;
import java.io.*;
public class Server 
{
	/**
	 * Main function that will create a server port and call onto the client to connect
	 * We will create a new computer object in order to store our patterns and to create a pattern data to store our patterns
	 * Function will also be able to save the game once user hits the Quit button
	 * Function will check what user input is in order to add the character into a pattern and once pattern hits over 3 characters then we will store the pattern
	 * pattern will be stored by taking the substring of the string and storing into the pattern data
	 * function will also make a prediction based on pattern and will hopefully beat the user
	 * If user inputs the Quit button then program will save the pattern data and program will turn off
	 */
	public static void main(String [] args)
	{
		boolean loop = true;
		try{
			ServerSocket server = new ServerSocket(1235);
			System.out.println("Waiting...");
			Socket sock = server.accept();
			System.out.println("Connected.");
			Computer comp = new Computer();
			File f = new File("pattern.dat");
			
			if(f.exists())
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
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			PrintStream out = new PrintStream(sock.getOutputStream());
			
			String letters = "";
			String pattern = "";
			
			while(loop)
			{
				
				String clicked = in.readLine();
				String choice = clicked;
				
				if(choice.equals("F"))
				{
					letters = "F";
					//moves = "Fire";
				}
					
				else if(choice.equals("W"))
				{
					letters = "W";
					//moves = "Water";
				}
					
				else if(choice.equals("G"))
				{
					letters = "G";
					//moves = "Grass";
				}
				
				else if(choice.equals("Q"))
				{
					
					System.out.println("Your game is saved!");
					try
					{
						ObjectOutputStream out1 = new ObjectOutputStream(new FileOutputStream(f));
						out1.writeObject(comp);
						out1.close();
					}catch(IOException e)
					{
						System.out.println("Error processing file");
					}
					loop = false;
					sock.close();
					System.exit(1);
					break;
					
				}
				
				String computerCheck = comp.makePrediction(pattern);
				out.println(computerCheck);
				//System.out.println(computerCheck);
					
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
				
			}
			
		}catch(IOException e)
		{
			System.out.println("Error processing file.");
		}
	}
}
