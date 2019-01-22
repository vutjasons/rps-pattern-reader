/**
 * Class takes a string and creates the Pattern object from that string
 * The class will be able to get the Pattern of the string
 * The class will be able to create a hashCode for the given pattern
 * The class will be able to compare the Objects to make sure they are equal
 */
import java.io.Serializable;
/**
 * Class stores a String parameter into the Pattern object
 * Class will store the pattern with a hashcode
 * Class will override a equals
 * @author jasonvu
 *
 */
public class Pattern implements Serializable
{
	/** Variable type String pattern**/
	private String pattern;
	
	/**
	 * Constructor for Pattern class that makes the pattern String as parameter p
	 * @param p - a string that contains a certain about of letters 
	 */
	public Pattern(String p)
	{
		pattern = p;
	}
	
	/**
	 * returns the Pattern String
	 * @return - the letters contained inside the pattern
	 */
	public String getPattern()
	{
		return pattern;
	}
	
	/**
	 * Creates a randomized address from HashCode
	 * @return result - the address of the string pattern
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		
		result = prime * result + pattern.hashCode();
		return result;
	}
	
	/**
	 * Overrides equals function and compares the pattern
	 * If the object O is a Pattern object return that the pattern equals to each other
	 * @param Object O - Object to test that it is a Pattern Object
	 * @return false if Object O is not a Pattern Object
	 * @return pattern.equals(p1.pattern) - says that the two Objects are equal to each other so returns true
	 */
	@Override
	public boolean equals(Object o)
	{
		if( o instanceof Pattern)
		{
			Pattern p1 = (Pattern) o;
			
			return pattern.equals(p1.pattern);
		}
		
		return false;
	}
}
