import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class NextPermutation
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for (int i = 0; i < N; i++)
		{
			String s = sc.next();
			char[] array = s.toCharArray();
			if(array.length == 1 || (array.length == 2 && array[0] == array[1]))
				System.out.println("no answer");
			else
			{
				boolean found = false;
				int index = array.length - 2;
				
				// Find the first smallest character from the end and swap it with the first largest element from the end
				for (int j = array.length - 2; !found && j >= 0 ; j--)
				{
					for (int k = array.length-1; !found && k > j ; k--)
					{
						if(array[j] < array[k])
						{
							// Swap the characters
							char temp = array[j];
							array[j] = array[k];
							array[k] = temp;
							index = j;
							found = true;
						}
					}
				}
				
				if(!found)
				{
					System.out.println("no answer");
				}
				else
				{
					// Sort the characters remaining from the beginning swapped character
					if(index+1 < array.length - 1)
						Arrays.sort(array, index+1, array.length);
					
					String newString = new String(array);
					System.out.println(newString);
				}
				
			}
		}
		
		sc.close();
	}
}
