import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class UpVotes
{
	private static final int INVALID = -1;

	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[] array = new int[N];
		for (int i = 0; i < N; i++)
			array[i] = sc.nextInt();

		TreeMap<Integer, Integer> incrMap = constructSequenceMap(array, new Predicate()
		{
			@Override
			public boolean validate(int a, int b)
			{
				return a <= b;
			}
		});
		
		TreeMap<Integer, Integer> decrMap = constructSequenceMap(array, new Predicate()
		{
			@Override
			public boolean validate(int a, int b)
			{
				return a >= b;
			}
		});
		
		//---------------------------------------------------------------------------------------------------------------
		
		// Initial windows
		Window incrWindow = new Window(array, 0, K - 1, incrMap);
		incrWindow.compute();
		
		Window decrWindow = new Window(array, 0, K - 1, decrMap);
		decrWindow.compute();
		
		System.out.println(incrWindow.count - decrWindow.count);
		//---------------------------------------------------------------------------------------------------------------

		while(incrWindow.moveWindowToRight(true) && decrWindow.moveWindowToRight(false))
		{
			System.out.println(incrWindow.count - decrWindow.count);
		}
		
		sc.close();
	}
	
	private static TreeMap<Integer, Integer> constructSequenceMap(int[] array, Predicate predicate)
	{
		TreeMap<Integer, Integer> seqMap = new TreeMap<>();
		for (int i = 0; i < array.length - 1; i++)
		{
			int index = i;
			while (i < array.length - 1 && predicate.validate(array[i], array[i + 1]))
				i++;

			if (index != i)
				seqMap.put(index, i);
		}
		return seqMap;
	}
	
	private static interface Predicate
	{
		boolean validate(int a, int b);
	}
	
	private static class Window
	{
		final int[] arr;
		final TreeMap<Integer, Integer> map;
		int start;
		int end;
		long count = 0;
		
		public Window(int[] arr, int start, int end, TreeMap<Integer, Integer> seqMap)
		{
			this.arr = arr;
			this.map = seqMap;
			this.start = start;
			this.end = end;
		}
		
		private void compute()
		{
			int startSeq = start;
			int endSeq = INVALID;
			while(startSeq < end)
			{
				for (int i = startSeq; endSeq == INVALID && i < end; i++)
				{
					Integer e = map.get(i);
					if(e == null)
						continue;
					
					startSeq = i;
					endSeq = e.intValue();
				}
				
				// If not able to find such sequence
				if(endSeq == INVALID)
					break;
				
				if(endSeq > end)
					endSeq = end;
				
				long diff = endSeq - startSeq + 1L;
				count += (diff*(diff - 1))/2;
				
				startSeq = endSeq + 1;
				endSeq = INVALID;
			}
		}
		
		private boolean moveWindowToRight(boolean increasing)
		{
			if(end + 1 == arr.length)
				return false;
			
			int oldStart = start;
			int oldEnd = end;
			
			start++;
			end++;
			
			int oldStartFloor = -1;
			Entry<Integer, Integer> oldStartEntry = map.floorEntry(oldStart);
			if(oldStartEntry != null)
			{
				Integer e = oldStartEntry.getValue();
				if(e.intValue() > oldStart)
				{
					oldStartFloor = oldStartEntry.getKey();
					count -= getDiff(oldStart, oldEnd, e);
				}
			}

			int oldEndFloor = -1;
			Entry<Integer, Integer> oldEndEntry = map.floorEntry(oldEnd);
			if(oldEndEntry != null)
			{
				if(oldEndEntry.getKey() > oldStartFloor)
				{
					Integer e = oldEndEntry.getValue();
					if(e.intValue() > oldEnd)
					{
						oldEndFloor = oldEndEntry.getKey();
						count -= getDiff(oldEndFloor, oldEnd, oldEnd);
					}
				}
			}
			
			if(increasing)
			{
				if(oldStartFloor != -1 || oldEndFloor != -1)
				{
					if(oldEndFloor != -1)
					{
						if(arr[end] >= arr[oldEnd])
							count += getDiff(oldEndFloor, end, end);
						else
							count += getDiff(oldEndFloor, oldEnd, oldEnd);
					}
					
					if(oldStartFloor != -1)
					{
						if(arr[start] >= arr[oldStart])
							count += getDiff(start, end, oldStartEntry.getValue());
						else
							count += getDiff(oldStart, oldEnd, oldStartEntry.getValue());
					}
				}
			}
			else
			{
				if(oldStartFloor != -1 || oldEndFloor != -1)
				{
					if(oldEndFloor != -1)
					{
						if(arr[end] <= arr[oldEnd])
							count += getDiff(oldEndFloor, end, end);
						else
							count += getDiff(oldEndFloor, oldEnd, oldEnd);
					}
					
					if(oldStartFloor != -1)
					{
						if(arr[start] <= arr[oldStart])
							count += getDiff(start, end, oldStartEntry.getValue());
						else
							count += getDiff(oldStart, oldEnd, oldStartEntry.getValue());
					}
				}
			}
			
			return true;
		}

		private long getDiff(int startIdx, int endIdx, int actualEnd)
		{
			if(actualEnd > endIdx)
				actualEnd = endIdx;
			long diff = actualEnd - startIdx + 1;
			return (diff*(diff-1))/2;
		}
	}
	
}
