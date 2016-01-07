import java.util.Scanner;

public class PascalTriangle
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		// Input is a single integer that says how many levels of pascal triangle to be printed.
		int N = sc.nextInt();
		
		int[] array = new int[N];
		array[0] = 1;
		print(array, 0);
		
		array[1] = 1;
		print(array, 1);
		
		for (int i = 2; i < N; i++)
		{
			int prev = 1;
			for (int j = 1; j < i; j++)
			{
				int v = array[j];
				array[j] = prev + v;
				prev = v;
			}
			array[i] = 1;
			
			print(array, i);
		}

		sc.close();
	}
	
	private static void print(int[] array, int end)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(array[0]);
		
		for (int i = 1; i <= end; i++)
			sb.append(" ").append(array[i]);
		
		System.out.println(sb.toString());
	}
}
