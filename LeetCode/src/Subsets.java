import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets
{

	public static void main(String[] args)
	{
		Subsets s = new Subsets();
		System.out.println(s.subsets(new int[]{1,2,3}));
	}

	public List<List<Integer>> subsets(int[] nums)
	{
		List<List<Integer>> result = new ArrayList<>();
		if(nums == null || nums.length == 0)
			return result;
		
		Arrays.sort(nums);
		subsets(new ArrayList<>(), nums, 0, result);
		return result;
	}
	
	private void subsets(List<Integer> prefix, int[] nums, int start, List<List<Integer>> result)
	{
		result.add(prefix);
		
		for (int i = start; i < nums.length; i++)
		{
			List<Integer> newList = new ArrayList<>(prefix);
			newList.add(nums[i]);
			subsets(newList, nums, i+1, result);
		}
	}
}
