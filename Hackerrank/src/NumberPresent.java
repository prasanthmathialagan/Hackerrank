import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NumberPresent
{
	public static void main(String[] args)
	{
		String[] pattern = BitPattern(new int[]{1,3,2,3,4,1});
		System.out.println(Arrays.toString(pattern));
	}

	private static String[] BitPattern(int[] num)
	{
		return new String[] { findResult(num, true), findResult(num, false) };
	}

	private static String findResult(int[] num, boolean forward)
	{
		char[] result = new char[num.length];
		Set<Integer> set = new HashSet<>();
		Arrays.fill(result, '0');

		if (forward)
		{
			for (int i = 0; i < num.length; i++)
				updateResult(num, result, set, i);
		}
		else
		{
			for (int i = num.length - 1; i >= 0; i--)
				updateResult(num, result, set, i);
		}

		return new String(result);
	}

	private static void updateResult(int[] num, char[] result, Set<Integer> set, int i)
	{
		int v = num[i];
		if (set.contains(v))
			result[i] = '1';
		else
			set.add(v);
	}
}
