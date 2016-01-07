import java.util.Scanner;

public class Median
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = sc.nextInt();

		int median = N / 2;
		int start = 0;
		int end = N - 1;
		while (true)
		{
			int index = partition(arr, start, end);
			if (index == median)
				break;
			if (index < median)
				start = index + 1;
			else
				end = index - 1;
		}

		System.out.println(arr[median]);

		sc.close();
	}

	private static int partition(int arr[], int start, int end)
	{
		int pivot = arr[end];
		int p = start; // Left of the subarray
		for (int i = p; i < end; i++)
		{
			if (arr[i] < pivot)
			{
				// Swap the element to the left of the sub array and at the
				// position i
				int temp = arr[p];
				arr[p] = arr[i];
				arr[i] = temp;

				p++;
			}
		}

		// Swap the left element of the subarray and the pivot element
		arr[end] = arr[p];
		arr[p] = pivot;

		return p;
	}

}
