package euler;

import java.util.Scanner;

// https://www.hackerrank.com/contests/projecteuler/challenges/euler002
public class EvenFibonacciNumbers
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		long[] evenFib = new long[40];
		evenFib[0] = 2;
		evenFib[1] = 8;
		
		for (int i = 2; i < evenFib.length; i++)
			evenFib[i] = (evenFib[i-1] << 2) + evenFib[i-2]; 
		
		int N = sc.nextInt();
		while(N-- > 0)
		{
			long limit = sc.nextLong();
			
			long sum = 0;
			for (int i = 0; i < evenFib.length; i++)
			{
				if(evenFib[i] > limit)
					break;
				sum += evenFib[i];
			}
			
			System.out.println(sum);
		}
		
		sc.close();
	}
}
