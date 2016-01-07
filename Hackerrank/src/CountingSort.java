import java.util.Scanner;

public class CountingSort
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[100];

		int n = sc.nextInt();
		while (n-- > 0)
		{
			int val = sc.nextInt();
			arr[val] = arr[val] + 1;
		}

		for (int i = 0; i < arr.length; i++)
		{
			int j = arr[i];
			while (j-- > 0)
			{
				System.out.print(i + " ");
			}
		}

		sc.close();
	}

}
