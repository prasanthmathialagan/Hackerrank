import java.util.Arrays;

/**
 * 
 * @author Prasanth
 *
 */
public class RemoveDupsFromSortedArray
{
	public static void main(String[] args)
	{
		int[] arr = new int[]{1,1,2,3,4,4,4, 5, 5, 6};
		System.out.println(removeDuplicates(arr));
		System.out.println(Arrays.toString(arr));
	}

	public static int removeDuplicates(int[] nums)
	{
		if(nums == null || nums.length == 0)
			return 0;
		
		if(nums.length == 1)
			return 1;
		
		// Find the initial positions of write and read pointers
		int writePtr = 1;
		while(writePtr < nums.length && nums[writePtr] != nums[writePtr - 1])
			writePtr ++;

		// Difference can be 0 or 1
		if(nums.length - writePtr <= 1)
			return writePtr;
		
		int readPtr = writePtr + 1;
		while(readPtr < nums.length)
		{
			if(nums[readPtr] != nums[readPtr - 1])
				nums[writePtr++] =  nums[readPtr];
			readPtr++;
		}
		
		return writePtr;
	}
}
