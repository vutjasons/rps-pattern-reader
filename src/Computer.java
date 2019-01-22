
/**
 * Class Computer creates a hashmap to store patterns in it
 * Will have the function to predict a what the user will choose based on patterns stored within hashmap
 * Will have the function to store patterns inside the hashmap
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

public class Computer implements Serializable {
	
	/**Create a new hashmap to store Object Pattern inside**/
	private HashMap<Pattern, Integer> map;
	
	/**
	 * Constructor for the Computer class which makes a new Hashmap called map
	 */
	public Computer()
	{
		map = new HashMap<Pattern, Integer>();
	}
	
	/**
	 * Creates three Patterns that will store the string pat parameter and we will create variables that will hold the Values of each pattern
	 * Will check to make sure that the pattern is within a length of at least 3 and then will attach a F,W,G to the end of the string and store into a new pattern
	 * map will check if the pattern is contained in each new string and will update the Values for each element
	 * Will compare values between the three patterns and whichever one has the highest value or if the values are all equal
	 * If pattern is not within Hashmap then we will randomly predict what F,W,G string to return
	 * will Return the correct F,W,G element to beat opponent
	 * @param pat - pattern passed in as the parameter
	 * @return - a string "F", "W", or "G"
	 */
	public String makePrediction(String pat)
	{
		
		Random rand = new Random();
		Pattern fire = new Pattern(pat);
		Pattern water = new Pattern(pat);
		Pattern grass = new Pattern(pat);

		String patter = fire.getPattern();
		
		int fireVal = 0;
		int waterVal = 0;
		int grassVal = 0;

		if(patter.length() == 3)
		{
			String patterT = patter.substring(1,patter.length()) + "F";
			String patterW = patter.substring(1,patter.length()) + "W";
			String patterG = patter.substring(1,patter.length()) + "G";
			
			fire = new Pattern(patterT);
			water = new Pattern(patterW);
			grass = new Pattern(patterG);
			
			if(map.containsKey(fire))
			{
				fireVal = map.get(fire);
			}
			if(map.containsKey(water))
			{
				waterVal = map.get(water);
			}

			if(map.containsKey(grass))
			{
				grassVal = map.get(grass);
			}

			if(fireVal > waterVal && fireVal > grassVal)
			{
				return "W";
			}
		
			else if(waterVal > fireVal && waterVal > grassVal)
			{
				return "G";
			}
			
			else if(grassVal > fireVal && grassVal > waterVal)
			{
				return "F";
			}
			
			else if(waterVal == fireVal && fireVal == grassVal)
			{
				int guess = rand.nextInt(3)+1;
				
				if(guess == 1)
				{
					return "F";
				}
					
				else if( guess == 2)
				{
					return "W";
				}
				else
				{
					return "G";
				}
				
			}
		}
		
		else
		{
		
		int guess = rand.nextInt(3)+1;
		
		if(guess == 1)
		{
			return "F";
		}
			
		else if( guess == 2)
		{
			return "W";
		}
		else
		{
			return "G";
		}
		
		}
		
		return null;
	}
	
	/**
	 * stores pattern within the hasmap
	 * if map contains the key of pattern p
	 * increment the value by 1, "updating"
	 * @param p - pattern passed in as a parameter
	 */
	public void storePattern(String pat)
	{
		Pattern p = new Pattern(pat);
		
		if(map.containsKey(p))
		{
			map.put(p, map.get(p)+1);
		}
		
		else
		{
			map.put(p, 1);
		}
	}
	

}