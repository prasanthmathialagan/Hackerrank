package euler;

import java.util.Scanner;

// https://www.hackerrank.com/contests/projecteuler/challenges/euler001
public class MultiplesOf5and3
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		while(N-- > 0)
		{
			int an = sc.nextInt() - 1;// below the given number
			long _3sum = sumAP(3, 3, an);
			long _5sum = sumAP(5, 5, an);
			long _15sum = sumAP(15, 15, an);
			
			System.out.println(_3sum + _5sum - _15sum);
		}
		
		sc.close();
	}

	private static long sumAP(long a0, long d, long an)
	{
		if(an < a0)
			return 0;
		
		long n = ((an - a0)/d) + 1;
		long sum = n*a0 + ((n-1)*n*d)/2;
		return sum;
	}
}
