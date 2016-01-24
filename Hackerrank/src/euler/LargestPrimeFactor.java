package euler;

import java.util.Scanner;

// https://www.hackerrank.com/contests/projecteuler/challenges/euler003
public class LargestPrimeFactor
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		while(N-- > 0)
		{
			long limit = sc.nextLong();
			while(limit % 2 == 0)
				limit = limit >> 1;

			if(limit == 1)
				System.out.println(2);
			else
			{
				boolean found = false;
				for (long i = 3; !false && i <= (long)Math.sqrt(limit); i += 2)
				{
					while(limit % i == 0)
						limit = limit / i;
					
					if(limit == 1)
					{
						System.out.println(i);
						found = true;
					}
				}
				
				if(!found)
					System.out.println(limit);
			}
		}
		
		sc.close();
	}
}
