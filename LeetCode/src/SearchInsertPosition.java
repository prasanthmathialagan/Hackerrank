
public class SearchInsertPosition
{

	public static void main(String[] args)
	{
		SearchInsertPosition p = new SearchInsertPosition();

		System.out.println(p.searchInsert(new int[] { 1, 3, 5, 6 }, 5));
		System.out.println(p.searchInsert(new int[] { 1, 3, 5, 6 }, 2));
		System.out.println(p.searchInsert(new int[] { 1, 3, 5, 6 }, 7));
		System.out.println(p.searchInsert(new int[] { 1, 3, 5, 6 }, 0));
		System.out.println(p.searchInsert(new int[] { 1, 3}, 2));
	}

	public int searchInsert(int[] nums, int target)
	{
		if (nums == null || nums.length == 0)
			return 0;

		return search(nums, target, 0, nums.length - 1);
	}

	private int search(int[] nums, int target, int start, int end)
	{
		// If there is only one element
		if (end == start)
		{
			if (nums[start] >= target)
				return start;
			return end + 1;
		}

		// If there are more than two elements
		int mid = (start + end) / 2;
		if (nums[mid] == target)
			return mid;
		else if (nums[mid] > target)
			return search(nums, target, start, mid - 1 >= start ? mid - 1 : start);
		else
			return search(nums, target, mid + 1 <= end ? mid + 1 : end, end);
	}
}
