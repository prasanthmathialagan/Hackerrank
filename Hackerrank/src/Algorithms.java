

import java.util.Arrays;
import java.util.Scanner;

public class Algorithms
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		String[] arr = new String[N];
		
		for (int i = 0; i < N; i++)
			arr[i] = sc.next();

		int teams = 0;
		int max = 0;
		
		for (int i = 0; i < N-1; i++)
		{
			for (int j = i + 1; j < N; j++)
			{
				String s = or(arr[i], arr[j]);
				int ones = ones(s);
				
				if(ones > max)
				{
					max = ones;
					teams = 1;
				}
				else if(ones == max)
				{
					teams++;
				}
			}
		}
		
		System.out.println(max);
		System.out.println(teams);
		
		sc.close();
	}
	
	// s and t are binary strings and of equal length
	private static String or(String s, String t)
	{
		char[] out = s.toCharArray();
		for (int i = 0; i < out.length; i++)
		{
			if(out[i] == '0' && t.charAt(i) == '1')
				out[i] = '1';
		}
		return new String(out);
	}
	
	// s is a binary string
	private static int ones(String s)
	{
		int count = 0;
		for (int i = 0; i < s.length(); i++)
		{
			if(s.charAt(i) == '1')
				count++;
		}
		return count;
	}
	
	private static void libraryFine(Scanner sc)
	{
		int d1 = sc.nextInt();
		int m1 = sc.nextInt();
		int y1 = sc.nextInt();
		
		int d2 = sc.nextInt();
		int m2 = sc.nextInt();
		int y2 = sc.nextInt();
		
		int fine = 0;
		if (y1 == y2)
		{
			if (m1 == m2)
			{
				if(d1 > d2)
					fine = (d1 - d2)*15;
			}
			else if (m1 > m2)
			{
				fine = (m1 - m2) * 500;
			}
		}
		else if (y1 > y2)
		{
			fine = 10000;
		}
		
		System.out.println(fine);
	}

	private static void cavityMap(Scanner sc)
	{
		int n = sc.nextInt();
		int[][] matrix = new int[n][n];
		populateMatrix(sc, n, n, matrix);
		
		int[][] copyMatrix = copyMatrix(n, matrix);
		
		for (int i = 1; i < n-1; i++)
		{
			for (int j = 1; j < n-1; j++)
			{
				int value = matrix[i][j];
				if(value > matrix[i][j-1] && value > matrix[i][j+1] && value > matrix[i-1][j] && value > matrix[i+1][j])
					copyMatrix[i][j] = -1;
			}
		}
		
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				System.out.print(copyMatrix[i][j] == -1 ? "X" : copyMatrix[i][j]);
			}
			
			System.out.println();
		}
	}

	private static int[][] copyMatrix(int n, int[][] matrix)
	{
		int[][] copyMatrix = new int[n][n];
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				copyMatrix[i][j] = matrix[i][j];
			}
		}
		return copyMatrix;
	}

	private static void gridSearch(Scanner sc)
	{
		int cases = sc.nextInt();
		
		while(cases-- > 0)
		{
			//populating the big matrix
			int R = sc.nextInt();
			int C = sc.nextInt();
			int[][] bigMatrix = new int[R][C];
			populateMatrix(sc, R, C, bigMatrix);

			// populating the small matrix
			int r = sc.nextInt();
			int c = sc.nextInt();
			int[][] smallMatrix = new int[r][c];
			populateMatrix(sc, r, c, smallMatrix);

			boolean found = false;
			for (int row = 0; !found && row <= R - r; row++)
			{
				for (int col = 0; !found && col <= C - c; col++)
				{
					if(bigMatrix[row][col] == smallMatrix[0][0])
					{
						boolean failed = false;
						
						for (int i = 0; !failed && i < r; i++)
						{
							for (int j = 0; !failed && j < c; j++)
							{
								if(bigMatrix[row + i][col + j] != smallMatrix[i][j])
									failed = true;
							}
						}
						
						found = !failed;
					}
				}
			}
			
			System.out.println(found ? "YES" : "NO");
		}
	}

	private static void populateMatrix(Scanner sc, int R, int C, int[][] bigMatrix)
	{
		for (int i = 0; i < R; i++)
		{
			String s = sc.next();
			char[] array = s.toCharArray();
			for (int j = 0; j < C; j++)
				bigMatrix[i][j] = Character.digit(array[j], 10);
		}
	}
	
	// From hackerrank
	 private static char caesarCipher(char c, int shift) {
	        if (!Character.isAlphabetic(c)) return c;
	        char base = 'A';
	        if (c >= 'a') base = 'a';
	        return (char)(((c - base + shift) % 26) + base);
	    }

	private static void caesarCipher(Scanner sc)
	{
		int n = sc.nextInt();
		String s = sc.next();
		int k = sc.nextInt() % 26;
		
		final int LOWER_MIN = 97;
		final int LOWER_MAX = 122;
		final int UPPER_MIN = 65;
		final int UPPER_MAX = 90;
		
		char[] arr = s.toCharArray();
		for (int i = 0; i < n; i++)
		{
			if(arr[i] >= LOWER_MIN && arr[i] <= LOWER_MAX)
			{
				arr[i] = (char) (arr[i] + k);
				if(arr[i] > LOWER_MAX)
				{
					int diff =  arr[i] - LOWER_MAX;
					arr[i] = (char) (LOWER_MIN + diff - 1);
				}
			}
			else if(arr[i] >= UPPER_MIN && arr[i] <= UPPER_MAX)
			{
				arr[i] = (char) (arr[i] + k);
				if(arr[i] > UPPER_MAX)
				{
					int diff =  arr[i] - UPPER_MAX;
					arr[i] = (char) (UPPER_MIN + diff - 1);
				}
			}
		}
		
		System.out.println(new String(arr));
	}

	private static void chocolateFeast(Scanner sc)
	{
		int cases = sc.nextInt();
		while(cases-- > 0)
		{
			int N = sc.nextInt();
			int C = sc.nextInt();
			int M = sc.nextInt();
			
			int n = N / C;
			int m = n;
			while(m >= M)
			{
				int nTemp = m / M;
				n = n + nTemp;
				m = nTemp + (m % M);
			}
			
			System.out.println(n);
		}
	}

	private static void cutTheSticks(Scanner sc)
	{
		int n = sc.nextInt();
		int arr[] = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = sc.nextInt();

		// O(nlogn)
		Arrays.sort(arr);
		
		int p = 0;
		while(p < n)
		{
			System.out.println(n - p);
			int min = arr[p];
			for (int i = p; i < n; i++)
			{
				arr[i] = arr[i] - min;
				if(arr[i] == 0)
					p++;
			}
		}
	}

	private static void serviceLane(Scanner sc)
	{
		int length = sc.nextInt();
		int cases = sc.nextInt();
		
		// Construct the width array
		int[] width = new int[length];
		for (int i = 0; i < length; i++)
			width[i] = sc.nextInt();
		
		while(cases-- > 0)
		{
			int i = sc.nextInt();
			int j = sc.nextInt();
			
			int min = width[i];
			for (int k = i + 1; k <= j; k++)
				min = Integer.min(min, width[k]);
			
			System.out.println(min);
		}
	}

	private static void findDigits(Scanner sc, int cases)
	{
		for (int i = 0; i < cases; i++)
		{
			long N = sc.nextLong();
			long temp = N;
			int count = 0;
			while(temp > 0)
			{
				long div_mul = (temp / 10)*10;
				long diff = temp - div_mul;
				if(diff > 0 && N % diff == 0)
					count++;
				temp = temp / 10;
			}
			
			System.out.println(count);
		}
	}

	private static void utopianTree(Scanner sc, int cases)
	{
		for (int i = 0; i < cases; i++)
		{
			int N = sc.nextInt();
			
			int size = 1;
			
			int p = N / 2;
			int q = N % 2;
			
			for (int j = 0; j < p; j++)
				size = size*2 + 1;

			for (int j = 0; j < q; j++)
				size = size*2;
			
			System.out.println(size);
		}
	}
}
