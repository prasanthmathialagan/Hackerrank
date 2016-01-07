import java.util.Arrays;
import java.util.Scanner;

public class GridChallenge
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		while (T-- > 0)
		{
			int N = sc.nextInt();
			char[][] matrix = new char[N][N];

			sc.nextLine();

			for (int i = 0; i < N; i++)
				matrix[i] = sc.nextLine().toCharArray();

			// Sort rows
			sort(matrix);

			// Take transpose
			matrix = transpose(matrix);

			boolean sorted = true;
			for (int i = 0; sorted && i < N; i++)
			{
				if (!isSorted(matrix[i]))
					sorted = false;
			}

			System.out.println(sorted ? "YES" : "NO");
		}
		sc.close();
	}

	private static void sort(char[][] matrix)
	{
		for (int i = 0; i < matrix.length; i++)
			Arrays.sort(matrix[i]);
	}

	private static char[][] transpose(char[][] matrix)
	{
		char[][] temp = new char[matrix.length][matrix.length];
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix.length; j++)
				temp[j][i] = matrix[i][j];
		return temp;
	}

	public static boolean isSorted(char[] a)
	{
		// assume is sorted, attempt to prove otherwise
		for (int i = 0; i < a.length - 1; i++)
		{ // because we are always comparing to the next one and the last one
			// doesn't have a next one we end the loop 1 earlier than usual
			if (a[i] > a[i + 1])
			{
				return false; // proven not sorted
			}
		}
		return true; // got to the end, must be sorted
	}
}
