import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cipher
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = reader.readLine();
		String[] split = line.split(" ");
		int N = Integer.parseInt(split[0]);
		int K = Integer.parseInt(split[1]);
		int S = N + K - 1;
		int[] input = new int[S];
		line = reader.readLine();
		for (int i = 0; i < line.length(); i++)
			input[i] = Character.getNumericValue(line.charAt(i));

		int[] output = new int[N];
		int allowedWinSize = Math.min(N-1, K-1);
		
		int windowStart = 0;
		int windowEnd = 0;
		int winXor = input[0];

		output[0] = input[0];
		
		 for (int i = 1; i < N; i++)
		{
			int val = winXor ^ input[i];
			output[i] = val;
			winXor = winXor ^ val;
			
			windowEnd++;
			
			// Check window size
			if(windowEnd - windowStart + 1 <= allowedWinSize)
				continue;
			
			winXor = winXor ^ output[windowStart];
			windowStart++;
		}
		 
		 for (int i = 0; i < output.length; i++)
			System.out.print(output[i]);

		 System.out.println();
		 
		reader.close();
	}
}
