import java.util.Scanner;

// https://www.hackerrank.com/contests/101hack33/challenges/max-sum-subarray
public class MaxSumSubArray
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int input[] = new int[N];
		for (int i = 0; i < N; i++)
			input[i] = sc.nextInt();
		
		long maxSum = 0;
		long maxSumTemp = 0;
		for (int i = 0; i < N; i++)
		{
			long val = input[i];
			if(val == 0)
				maxSumTemp = 0;
			else
			{
				maxSumTemp = maxSumTemp + val;
				if(maxSumTemp < 0)
					maxSumTemp = 0;
			}
			maxSum = Math.max(maxSum, maxSumTemp);
		}
		
		System.out.println(maxSum);

		sc.close();
	}
}
