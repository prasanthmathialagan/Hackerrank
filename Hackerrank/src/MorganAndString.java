import java.util.Scanner;

public class MorganAndString
{
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);

		// Test cases
		int T = sc.nextInt();
		sc.nextLine();

		while (T-- > 0)
		{
			char[] A = sc.nextLine().toCharArray();
			char[] B = sc.nextLine().toCharArray();

			int N = A.length;
			int M = B.length;

			int p = 0;
			int q = 0;
			while (p < N && q < M)
			{
				int diff = A[p] - B[q];
				if (diff < 0) // A[p] < B[q]
					System.out.print(A[p++]);
				else if (diff > 0) // A[p] > B[q]
					System.out.print(B[q++]);
				else // if both the chars are same
				{
					int p_ = p + 1;
					int q_ = q + 1;

					boolean found = false;
					while (!found && p_ < N && q_ < M)
					{
						int sdiff = A[p_] - B[q_];
						if (sdiff == 0)
						{
							p_++;
							q_++;
						} else
						{
							found = true;
							if (sdiff < 0)
							{
								char c = A[p];
								System.out.print(A[p++]);
								while (p < p_ && A[p] == c)
									System.out.print(A[p++]);
							} else
							{
								char c = B[q];
								System.out.print(B[q++]);
								while (q < q_ && B[q] == c)
									System.out.print(B[q++]);
							}
						}
					}

					if (!found) // Can chose either A or B
					{
						if (p_ == N) // if A reached it's end
						{
							char c = B[q];
							System.out.print(B[q++]);
							while (q < q_ && B[q] == c)
								System.out.print(B[q++]);
						} else
						{
							char c = A[p];
							System.out.print(A[p++]);
							while (p < p_ && A[p] == c)
								System.out.print(A[p++]);
						}
					}
				}
			}

			// if A reached it's end, print B till the end
			if (p == N)
			{
				while (q < M)
					System.out.print(B[q++]);
			}
			// if B reached it's end, print A till the end
			else if (q == M)
			{
				while (p < N)
					System.out.print(A[p++]);
			}

			System.out.println();
		}
		sc.close();
	}
}
