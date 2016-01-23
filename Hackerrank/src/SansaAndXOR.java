import java.util.Scanner;

// https://www.hackerrank.com/challenges/sansa-and-xor
public class SansaAndXOR
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		while(N-- > 0)
		{
			int M = sc.nextInt();
			int[] arr = new int[M];
			for (int i = 0; i < M; i++)
				arr[i] = sc.nextInt();
			if(arr.length % 2 == 0) // If the array size is even 
			{
				System.out.println(0);
			}
			else
			{
				int sum = 0;
				for (int i = 0; i < arr.length; i += 2)
					sum = sum ^ arr[i];
				System.out.println(sum);
			}
		}
		
		sc.close();
	}
}
