import java.util.Scanner;

public class NewYearChaos
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		while(N-- > 0)
		{
			int M = sc.nextInt();
			int[] result = new int[M+1];
			int[] input = new int[M+1];
			for (int i = 1; i <= M; i++)
				input[i] = sc.nextInt();
			
			int i = 1;
			while (i <= M)
			{
				if(input[i] < i)
				{
					int dest = input[i];
					while(i > dest)
					{
							int temp = input[i-1];
							input[i-1] = input[i];
							input[i] = temp;
							i--;
							
							result[temp] += 1;
					}
				}
				i++;
			}
			
			int count = 0;
			boolean valid = true;
			for (int j = 0; valid && j < result.length; j++)
			{
				if(result[j] > 2)
					valid = false;
				else
					count += result[j];
			}
			
			if(valid)
				System.out.println(count);
			else
				System.out.println("Too chaotic");
		}
		
		sc.close();
	}
}
