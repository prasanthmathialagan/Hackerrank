import java.util.Scanner;

// https://www.hackerrank.com/contests/infinitum14/challenges/most-distant
public class MostDistant
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		int minX = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;

		int minY = Integer.MAX_VALUE;
		int maxY = Integer.MIN_VALUE;

		int N = sc.nextInt();
		while (N-- > 0)
		{
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			minX = Math.min(minX, x);
			minY = Math.min(minY, y);
			
			maxX = Math.max(maxX, x);
			maxY = Math.max(maxY, y);
		}
		
		double maxDist = maxX - minX;
		maxDist = Math.max(maxDist, maxY - minY);
		maxDist = Math.max(maxDist, distance(minX, minY));
		maxDist = Math.max(maxDist, distance(maxX, maxY));
		maxDist = Math.max(maxDist, distance(minX, maxY));
		maxDist = Math.max(maxDist, distance(maxX, minY));
		
		System.out.printf("%.6f", maxDist);
		
		sc.close();
	}
	
	private static double distance(int x, int y)
	{
		double xDiff = Math.pow(x, 2);
		double yDiff = Math.pow(y, 2);
		return Math.sqrt(xDiff + yDiff);
	}
}
