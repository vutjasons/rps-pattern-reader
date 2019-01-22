
public class test {
	public static void main(String args[])
	{
		int[] numbers = new int[3];
		numbers[0] = 2;
		numbers[1] = 1;
		numbers[2] = 3;
		int temp;

		for (int i = 0; i < 3; i++)
		{
		       for(int j = 0; j < 2; j++)
		       {
		                if(numbers[i] < numbers[j])
		                {
		                            temp = numbers [j];
		                            numbers [j]= numbers [i];
		                            numbers [i] = temp;
		                }
		        }
		}
		System.out.println(numbers[0]+","+numbers[1] +","+numbers[2]);
		
	}

}
