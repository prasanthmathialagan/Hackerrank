import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SherlockAndWatson {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream("inputSH.txt"));
		int N = sc.nextInt();
		int K = sc.nextInt();
		int Q = sc.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		for (int i = 0; i < Q; i++) {
			int idx = sc.nextInt();
			int midx = getMappedIndex(idx, K, N);
			System.out.println(arr[midx]);
		}
		
		sc.close();
	}
	
	private static int getMappedIndex(int x, int K, int N)
	{
		int p = x - K;
		while(p < 0)
			p = N + p;
		return p % N;
	}

}
