import java.util.Scanner;

public class SherlockAndPairs
{
	private static final int COUNT = 1000001;

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		while(T-- > 0)
		{
			int N = sc.nextInt();
			int arr[] = new int[COUNT];
			while(N-- > 0)
			{
				int i = sc.nextInt();
				arr[i] = arr[i] + 1;
			}
			
			long count = 0;
			for (int i = 0; i < arr.length; i++)
			{
				int val = arr[i];
				if(val > 1)
				{
					long n = val;
					n = n*(n-1);
					count = count + n;
				}
			}
			
			System.out.println(count);
		}
		
		sc.close();
	}
}
