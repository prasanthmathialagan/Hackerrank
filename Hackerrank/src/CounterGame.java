import java.math.BigInteger;
import java.util.Scanner;

public class CounterGame
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		while(N-- > 0)
		{
			BigInteger bigint = sc.nextBigInteger();
			bigint = bigint.subtract(BigInteger.ONE);
			
			int operations = 0;
			while(!BigInteger.ZERO.equals(bigint))
			{
				bigint = bigint.and(bigint.subtract(BigInteger.ONE));
				operations++;
			}
			
			if(operations % 2 != 0)
				System.out.println("Louise");
			else
				System.out.println("Richard");
		}
		sc.close();
	}
}
