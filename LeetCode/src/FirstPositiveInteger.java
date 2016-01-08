public class FirstPositiveInteger
{
	public static void main(String[] args)
	{
		System.out.println(firstMissingPositive(new int[] { 1, 2, 0 }));
		System.out.println(firstMissingPositive(new int[] { 3, 4, -1, 1 }));
		System.out.println(firstMissingPositive(new int[] { 7, 3, 2, 6, 4, 1 }));
		System.out.println(firstMissingPositive(new int[] { 0, 1, 2, 3, 3, 4 }));
		System.out.println(firstMissingPositive(new int[] { -1, 0, -4, 2, 3, 4 }));
		System.out.println(firstMissingPositive(new int[] { 0 }));
	}

	public static int firstMissingPositive(int[] nums)
	{
		int i = 0;
		int j = nums.length - 1;

		// Segregate positive and negative numbers
		while (i <= j)
		{
			if (nums[i] <= 0)
				i++;
			else if (nums[j] > 0)
				j--;
			else
			{
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
				i++;
				j--;
			}
		}

		int end = nums.length - i;

		// Set negative value for the index found at the given index
		for (int k = i; k < nums.length; k++)
		{
			int v = Math.abs(nums[k]);
			if (v > end)
				continue;

			nums[i + v - 1] = 0 - Math.abs(nums[i + v - 1]);
		}

		// Find the index with first positive value
		for (int k = i; k < nums.length; k++)
		{
			if (nums[k] <= 0)
				continue;

			return k - i + 1;
		}

		return nums.length - i + 1;
	}
}
