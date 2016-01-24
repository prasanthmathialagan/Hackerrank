
/**
 * 
 * @author Prasanth
 *
 */
public class SearchInSortedRotatedArray
{
	public static void main(String[] args)
	{
		System.out.println(search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 7));
		System.out.println(search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 8));
		System.out.println(search(new int[] { 7, 0, 1, 2, 4, 5, 6 }, 7));

		System.out.println(search(new int[] { 5, 6, 7, 0, 1, 2, 3 }, 7));
		System.out.println(search(new int[] { 1, 2, 3, 5, 6, 7, 0 }, 7));
		System.out.println(search(new int[] { 1, 3 }, 4));
		System.out.println(search(new int[] { 1, 3 }, 0));
	}

	public static int search(int[] nums, int target)
	{
		if (nums == null || nums.length == 0)
			return -1;

		// find the pivot
		int pivot = findPivot(nums, 0, nums.length - 1);

		int start = pivot;
		int end = (start + nums.length - 1) % nums.length;
		return search(nums, target, start, end);
	}

	private static int search(int[] nums, int target, int start, int end)
	{
		// If there is only one element
		if (end == start)
			return nums[start] == target ? start : -1;
		
		int n = (end - start + nums.length) % nums.length + 1;
		// If there are only two elements
		if(n == 2)
		{
			if(nums[start] == target)
				return start;
			if(nums[end] == target)
				return end;
			return -1;
		}
		
		// If there are more than two elements
		int mid = (start + n/2 + nums.length) % nums.length;
		if (nums[mid] == target)
			return mid;
		else if (nums[mid] > target)
			return search(nums, target, start, (mid - 1 + nums.length) % nums.length);
		else
			return search(nums, target, (mid + 1 + nums.length) % nums.length, end);
	}

	private static int findPivot(int[] nums, int start, int end)
	{
		// If there are less than or equal to two elements
		if (end - start <= 1)
			return nums[start] < nums[end] ? start : end;

		// If there are more than two elements
		int mid = (start + end) / 2;
		if (nums[mid] > nums[end])
			return findPivot(nums, mid, end);
		else
			return findPivot(nums, start, mid);
	}
}
