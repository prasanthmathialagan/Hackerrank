import java.util.Arrays;
import java.util.Scanner;

public class ClosestNumbers
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int arr[] = new int[N];

		for (int i = 0; i < N; i++)
		{
			arr[i] = sc.nextInt();
		}

		Arrays.sort(arr); // O(nlogn)

		// O(n)
		int smallDiff = arr[1] - arr[0];
		for (int i = 1; i < arr.length - 1; i++)
		{
			int diff = arr[i + 1] - arr[i];
			if (diff < smallDiff)
				smallDiff = diff;
		}

		for (int i = 0; i < arr.length - 1; i++)
		{
			if (arr[i + 1] - arr[i] == smallDiff)
				System.out.print(arr[i] + " " + arr[i + 1] + " ");
		}

		sc.close();
	}
}
