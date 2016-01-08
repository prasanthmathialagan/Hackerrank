

import java.util.Scanner;

public class QuickSort
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] arr = new int[N];

		for (int i = 0; i < N; i++)
			arr[i] = sc.nextInt();

		sort(arr, 0, arr.length-1);
		
		printArray(arr);
		
		sc.close();
	}
	
	private static void sort(int[] arr, int start, int end)
	{
		if(end - start <= 0)
			return;
		
		int i = partition(arr, start, end);
		sort(arr, start, i-1);
		sort(arr, i+1, end);
	}
	
	// returns the index of the partition
	private static int partition(int arr[], int start, int end)
	{
		int pivot = arr[end];
		int p = start;
		for (int i = start; i < end; i++)
		{
			if(pivot > arr[i])
			{
				// Move the element to the left of the wall
				// swap the element at p and i
				int temp = arr[p];
				arr[p] = arr[i];
				arr[i] = temp;
				
				p++;
			}
		}
		
		int temp = arr[p];
		arr[p] = pivot;
		arr[end] = temp;
		
		return p;
	}
	
	public static void printArray(int[] ar)
	{
		System.out.print(ar[0]);
		for (int i = 1; i < ar.length; i++)
			System.out.print(" " + ar[i]);
		System.out.println();
	}
}
