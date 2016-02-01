
public class MergeSortedArrays
{
	public static void main(String[] args)
	{

	}

	public void merge(int[] nums1, int m, int[] nums2, int n)
	{
		int ptr = m + n - 1;

		int i = m - 1;
		int j = n - 1;
		
		while(i >= 0 && j >= 0)
		{
			if(nums1[i] >= nums2[j])
				nums1[ptr--] = nums1[i--];
			else
				nums1[ptr--] = nums2[j--];
		}
		
		while(j >= 0)
			nums1[ptr--] = nums2[j--];
		
		while(i >= 0)
			nums1[ptr--] = nums1[i--];
	}
}
