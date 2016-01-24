import java.util.Arrays;

/**
 * 
 * @author Prasanth
 *
 */
public class SearchForRange
{
	public static void main(String[] args)
	{
		SearchForRange s = new SearchForRange();
		System.out.println(Arrays.toString(s.searchRange(new int[] { 5, 7, 7, 8, 8, 10 }, 8)));
	}

	public int[] searchRange(int[] nums, int target)
	{
		int[] result = { -1, -1 };
		if (nums == null || nums.length == 0)
			return result;
		searchRange(nums, target, 0, nums.length - 1, result);
		return result;
	}

	private void searchRange(int[] nums, int target, int start, int end, int[] result)
	{
		// Only one element
		if (start == end)
		{
			if (nums[start] == target)
				updateResult(start, end, result);
			return;
		}
		
		// More than one element
		int mid = (start + end) / 2;
		if(nums[mid] == target)
			updateResult(mid, mid, result);
		if (mid < end && nums[mid] <= target)
			searchRange(nums, target, mid + 1, end, result);
		if (mid > start && nums[mid] >= target)
			searchRange(nums, target, start, mid - 1, result);
	}

	private void updateResult(int newStart, int newEnd, int result[])
	{
		if (result[0] == -1 || result[0] > newStart)
			result[0] = newStart;

		if (result[0] == -1 || result[1] < newEnd)
			result[1] = newEnd;
	}
}
