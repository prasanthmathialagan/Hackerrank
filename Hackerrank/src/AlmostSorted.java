import java.util.Arrays;
import java.util.Scanner;

public class AlmostSorted {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr1 = new int[N];
		for (int i = 0; i < N; i++) {
			arr1[i] = sc.nextInt();
		}
		sc.close();
		
		// Main logic
		int[] arr2 = Arrays.copyOf(arr1, N);
		
		// First case --> O(2n)
		firstCase(arr1);
		
		// Second case --> O(3n)
		secondCase(arr2);
	}

	private static void secondCase(int[] arr) {
		
		int N = arr.length;
		int P = -1;
		int Q = -1;
		for (int i = 0; i < N - 1; i++) {
			if(arr[i+1] > arr[i])
				continue;
			P = i;
			break;
		}
		
		for (int i = P + 1; i < N - 1; i++) {
			if(arr[i+1] < arr[i])
				continue;
			Q = i;
			break;
		}
		
		if(Q == P) {
			// It would be handled in the first case. So no action needed.
			// This should not occur.
		}
		else {
			if(Q < P)
			{
				// Q is not moved at all. Set Q = N - 1 and try the reverse
				Q = N - 1;
			}
			
			// Reverse the array from P to Q
			reverse(arr, P, Q);
			
			// Check
			if(isSorted(arr)){
				System.out.println("yes");
				System.out.println("reverse " + (P + 1) + " " + (Q + 1));
			}
			else {
				System.out.println("no");
			}
		}
	}

	private static void firstCase(int[] arr) {
		
		int N = arr.length;
		int P = -1;
		int Q = -1;
		for (int i = 0; i < N - 1; i++) {
			if(arr[i+1] > arr[i])
				continue;
			P = i;
			break;
		}
		
		// If P is the last before index, set Q to the last index
		if (P + 1 == N - 1) {
			Q = N - 1;
		} else {
			for (int i = P + 1; i < N - 1; i++) {
				if(arr[i+1] > arr[i])
					continue;
				Q = i + 1;
				break;
			}
		}

		// Q and P equals to -1 ==> the array is sorted
		if(Q == P) {
			System.out.println("yes");
			System.exit(0);
		}
		else {
			if(Q < P) {
				// Sometimes if we swap the element next to P, we might get the array sorted. It can be handled here by setting Q = P + 1.
				Q = P + 1;
			}
			
			// Swap the elements at Q and P
			int temp = arr[P];
			arr[P] = arr[Q];
			arr[Q] = temp;
			// Check if the array is sorted after swapping
			if(isSorted(arr)) {
				System.out.println("yes");
				System.out.println("swap " + (P + 1) + " " + (Q + 1));
				System.exit(0);
			}
			else {
				// Let the second case handle this scenario
			}
		}
	}

	private static boolean isSorted(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if(arr[i] > arr[i+1])
				return false;
		}
		
		return true;
	}
	
	private static void reverse(int arr[], int start, int end)
	{
		int n = end - start + 1;
		for (int i = 0; i < n/2; i++) {
			int iStart = start + i;
			int iEnd = end - i;
			
			// Swap
			int temp = arr[iStart];
			arr[iStart] = arr[iEnd];
			arr[iEnd] = temp;
		}
	}
}
