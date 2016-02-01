
public class Sqrt
{
	public static void main(String[] args)
	{
		Sqrt s = new Sqrt();
		System.out.println(s.mySqrt(2147395599));
		System.out.println(s.sqrt(2147395599));
	}

	public int mySqrt(int x)
	{
		if (x <= 1)
			return x;

		return (int) mySqrt0(x, 1, x >> 1);
	}

	private long mySqrt0(long x, long start, long end)
	{
		if (start >= end)
			return start;

		if (end - start == 1)
			return end * end <= x ? end : start;

		long mid = (start + end) / 2;
		long mid2 = mid * mid;
		if (mid2 == x)
			return mid;
		if (mid2 > x)
			return mySqrt0(x, start, mid - 1);

		return mySqrt0(x, mid, end);
	}

	// Another approach
	int sqrt(int x)
	{
		if (x == 0)
			return 0;
		double last = 0;
		double res = 1;
		while (res != last)
		{
			last = res;
			res = (res + x / res) / 2;
			System.out.println("Result = " + res);
		}
		return (int) res;
	}
}
